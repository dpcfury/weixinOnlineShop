package com.slf.wx.pay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slf.wx.email.service.EmailService;
import com.slf.wx.order.constants.OrderNotifyStatus;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.entity.OrderItem;
import com.slf.wx.order.service.OrderService;
import com.slf.wx.order.util.OIDGenerator;
import com.slf.wx.pay.constant.GzhaoConfig;
import com.slf.wx.pay.constant.PayStatus;
import com.slf.wx.pay.constant.PayWay;
import com.slf.wx.pay.service.WechatService;
import com.slf.wx.pay.util.MessageUtil;
import com.slf.wx.pay.util.WechatUtil;
import com.slf.wx.pay.util.WxPayResultData;
import com.slf.wx.pay.util.WxPaySendData;
import com.slf.wx.pay.util.WxSign;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.IpTools;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * Description：支付的Controller Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月12日
 * 
 */
@Controller
@RequestMapping(value = "/wxPay")
public class PayController {

	@Resource
	private OrderService orderService;

	@Resource
	private UserService userService;

	@Resource

	private WechatService wechatService;

	@Resource
	private EmailService emailService;

	private static final Logger logger = LogManager.getLogger(PayController.class);

	// 统一下单接口 生成订单持久化 并向微信发送统一下单调用
	// 发送http请求到微信统一下单接口
	// 返回prepareid 返回支付参数到前台页面
	@RequestMapping(value = "/unifiedorder", method = RequestMethod.POST)
	public String preOrder(Order order, String addressId, HttpServletRequest request, HttpSession session) {
		// 安全日志
		String ip = IpTools.getIpAddr(request);
		logger.info("ip:" + ip + "发起了一创建订单的请求");

		// 判断session中是否已经存在订单如果存在判断状态

		System.out.println("支付方式为:"+order.getPayWay());
		
		Iterator<OrderItem> it=order.getItems().iterator();
		while(it.hasNext()){
			System.out.println("item"+it.next());
		}
		
		// 创建订单，并持久化
		order.setoId(OIDGenerator.createOID());
		order.setCreateTime(new Date(System.currentTimeMillis()));
		order.setStatus(0);// 未支付状态
		User user = (User) session.getAttribute("currentUser");
		order.setUser(user);// 设置属于当前登录用户
		order.setAddress(userService.getUserAddress(user, addressId));// 设置收货地址
		order = orderService.addOrder(order);

		if (order.getPayWay() == PayWay.TICKET) {// 水票支付 直接跳转到订单显示界面
			session.setAttribute("order", order);// 把生成的订单信息放到session中
			
			//发送商户提示邮件
			emailService.sendOrderToCop(order);
			order.setNotifyFlag(OrderNotifyStatus.NOTIFY_DONE);
			// 更新订单邮件发送通知状态位
			orderService.updateOrder(order);
			logger.info("订单:" + order.getoId() + " 商户邮件提醒成功");
			return "/order/order_result";
		}else if(order.getPayWay() == PayWay.E_TICKET){//电子水票支付
			session.setAttribute("order", order);// 把生成的订单信息放到session中
			
			//扣除用户相应的水票信息
			int num =(int) (order.getSummary()/25.0);
			user.setTickets_left(user.getTickets_left()-num);
			userService.updateUser(user);
			logger.info("用户:"+user.getName()+"电子税票扣除成功");
			
			//发送商户提示邮件
			order.setStatus(PayStatus.PAY_DONE);
			emailService.sendOrderToCop(order);
			order.setNotifyFlag(OrderNotifyStatus.NOTIFY_DONE);
			orderService.updateOrder(order);
			
			logger.info("订单:" + order.getoId() + " 商户邮件提醒成功");
		
			return "/order/order_result";
			
		} else {// 微信支付方式
				// 创建随机数
			String nonceStr = WxSign.getNonceStr();

			// 准备统一下单参数
			WxPaySendData data = new WxPaySendData();
			data.setAppid(GzhaoConfig.APPID);
			data.setAttach("苏州咕噜信息科技有限公司");
			data.setBody("微信公众号订水");
			data.setMch_id(GzhaoConfig.MCH_ID);
			data.setNonce_str(nonceStr);
			data.setNotify_url(GzhaoConfig.PAY_NOTIFY_URL);
			data.setOut_trade_no(order.getoId());
			data.setTotal_fee((int) (order.getSummary() * 100));// 单位：分
//			data.setTotal_fee(1);
			data.setTrade_type(GzhaoConfig.TRADE_TYPE);
			data.setSpbill_create_ip(ip);
			data.setOpenid(user.getId());
			data.setTime_start(new SimpleDateFormat("yyyyMMddHHmmss").format(order.getCreateTime()));

			// 调用统一下单接口并接受返回参数
			String result = unifiedorder(data, GzhaoConfig.WX_PAY_KEY);

			XStream xstream = new XStream(new DomDriver());
			xstream.alias("xml", WxPayResultData.class);
			WxPayResultData resultData = (WxPayResultData) xstream.fromXML(result);
			logger.info("获取统一订单返回结果 result_code:" + resultData.getResult_code() + "---return_code:"
					+ resultData.getReturn_code() + "-----return_msg:" + resultData.getReturn_msg());

			if (resultData.getResult_code().equals("SUCCESS") && resultData.getReturn_code().equals("SUCCESS")) {
				// 生成sign
				SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
				signMap.put("appId", resultData.getAppid());
				signMap.put("timeStamp", WxSign.getTimeStamp());
				signMap.put("nonceStr", resultData.getNonce_str());
				signMap.put("package", "prepay_id=" + resultData.getPrepay_id());
				signMap.put("signType", "MD5");
				String paySin = WxSign.createSign(signMap, GzhaoConfig.WX_PAY_KEY);
				logger.info("生成的签名PaySIGN:" + paySin);
				signMap.put("paySign", paySin);
				session.setAttribute("order", order);// 把生成的订单信息放到model中
				session.setAttribute("payparameter", signMap);// 把生成的支付参数放到model中

				return "redirect:/wxPay/beginPay/";
			} else {
				logger.error("微信下单返回错误");
				return "/order/order_error";
			}
		}

	}

	@RequestMapping(value = "/beginPay/")
	public String orderSuccess() {
		// 重定向避免订单的重复提交
		return "/order/order_success";
	}

	@RequestMapping(value = "/showPayResult")
	public String payResult() {
		// 展示支付的结果
		return "/order/pay_ok";
	}

	// 统一下单调用
	public String unifiedorder(WxPaySendData data, String key) {
		// 统一下单支付
		String returnXml = null;
		try {
			// 生成sign签名
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", data.getAppid());
			parameters.put("attach", data.getAttach());
			parameters.put("body", data.getBody());
			parameters.put("mch_id", data.getMch_id());
			parameters.put("nonce_str", data.getNonce_str());
			parameters.put("notify_url", data.getNotify_url());
			parameters.put("out_trade_no", data.getOut_trade_no());
			parameters.put("total_fee", data.getTotal_fee());
			parameters.put("trade_type", data.getTrade_type());
			parameters.put("spbill_create_ip", data.getSpbill_create_ip());
			parameters.put("openid", data.getOpenid());
			parameters.put("device_info", data.getDevice_info());
			parameters.put("time_start", data.getTime_start());

			String sign = WxSign.createSign(parameters, key);
			logger.info("SIGN:" + sign);
			data.setSign(sign);

			XStream xs = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
			xs.alias("xml", WxPaySendData.class);
			String xml = xs.toXML(data);
			logger.info("统一下单xml为:\n" + xml);

			returnXml = wechatService.wexinOrder(GzhaoConfig.UNIFIDEORDER_URL, xml);
			logger.info("统一下单结果:" + returnXml);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用统一下单方法错误", e);
		}
		return returnXml;
	}

	// 微信支付的回调接口
	// 接受微信的支付通知
	// 判断是否已经处理过
	// 执行相应逻辑
	// 通知微信已经成功接收到通通知不必再发请求
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String payNotify(HttpSession session, HttpServletRequest request) {
		/*
		 * 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
		 * 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，
		 * 如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
		 * 特别提醒：商户系统对于支付结果通知的内容一定要做签名验证，防止数据泄漏导致出现“假通知”，造成资金损失。
		 * 技术人员可登进微信商户后台扫描加入接口报警群。
		 */
		try {
			// 1.解析微信通知发来的请求（和支付宝不同，微信返回的是流）
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			logger.info("微信支付回调request参数:");
			for (Entry<String, String> item : requestMap.entrySet()) {
				if (!item.getKey().equals("sign")) {
					parameters.put(item.getKey(), item.getValue());
				}
				// logger.info("key:" + item.getKey() + "---value:" +
				// item.getValue());
			}
			if (!requestMap.get("return_code").equals("SUCCESS")) {
				logger.info("微信支付通知返回错误信息:" + requestMap.get("return_msg"));
				return "SUCCESS";
			}

			// 2.签名验证(验证通过继续，不通过直接返回错误)
			String my_sign = WxSign.createSign(parameters, GzhaoConfig.WX_PAY_KEY);
			String wx_sign = requestMap.get("sign");
			if (!my_sign.equalsIgnoreCase(wx_sign)) {
				logger.info("签名验证失败");
				return "FAIL";// 就不是微信发送的通知
			}

			// 3.检查对应订单是否已经处理过（处理过直接返回成功通知，未处理过执行业务逻辑后再返回成功通知）
			String tradeNo = requestMap.get("out_trade_no");
			Order order = orderService.getOrderById(tradeNo);
			if (order.getStatus() == PayStatus.PAY_DONE) {
				// 代表完成支付代表已经处理过 返回成功信息给微信
				logger.info("订单:" + order.getoId() + "已经处理过");
				// return notifyReturnXML("SUCCESS", "");
				return "SUCCESS";
			} else {
				// 未处理过 -->进行相关业务逻辑的处理
				if (requestMap.get("result_code").equals("SUCCESS")) {
					// result_code和return_code同时为SUCCESS代表支付成功
					order.setStatus(PayStatus.PAY_DONE);

					// 更新订单支付状态位
					orderService.updateOrder(order);

					logger.info("订单:" + order.getoId() + " 支付完成");
					// 发送邮件到客服
					emailService.sendOrderToCop(order);// 要改成同步的发送不能异步
					order.setNotifyFlag(OrderNotifyStatus.NOTIFY_DONE);
					// 更新订单邮件发送通知状态位
					orderService.updateOrder(order);
					logger.info("订单:" + order.getoId() + " 商户邮件提醒成功");
					// }catch(Exception e){
					// logger.error("订单:"+order.getoId()+"发送商户提醒邮件失败");
					// }

					// 返回成功消息给微信
					return "SUCCESS";
				} else {
					// result_code不是SUCCESS
					logger.error("订单:" + order.getoId() + "支付失败");
					logger.error(requestMap.get("return_msg"));
					return "SUCCESS";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("接收微信支付回调出现错误", e);
			return "FAIL";
		}

	}

	/**
	 * 微信支付回调接口返回参数
	 * 
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public String notifyReturnXML(String return_code, String return_msg) {
		return "<xml><return_code><![DATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
}

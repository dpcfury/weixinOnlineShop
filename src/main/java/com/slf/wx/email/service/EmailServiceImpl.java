package com.slf.wx.email.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.slf.wx.email.constants.EmailConstants;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.entity.OrderItem;
import com.slf.wx.pay.constant.PayStatus;
import com.slf.wx.pay.constant.PayWay;
import com.slf.wx.user.entity.DeliveryAddress;

/**
 * Description:邮件服务接口的具体实现类 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月28日
 * 
 */
@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

	@Resource
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderToCop(Order Order) {
		logger.info("开启邮件发送");
		sendEmail(Order);

	}

	private class SendMailThread implements Runnable {
		private Order order;

		private SendMailThread(Order order) {
			super();
			this.order = order;
		}

		@Override
		public void run() {
			sendEmail(order);
		}

	}

	private void sendEmail(Order order) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(EmailConstants.mailFrom);
			helper.setTo(EmailConstants.mailTo);
			helper.setSubject("新订单:" + order.getUser().getName());
			helper.setSentDate(new Date());
			// message.setContent(createOrderInfo(order),"text/plain");;
			helper.setText(createOrderInfo(order));
			javaMailSender.send(message);
			logger.info("新订单" + order.getoId() + " 邮件提醒发送成功");
		} catch (MessagingException e) {
			logger.error("订单:" + order.getoId() + "商户提醒邮件发送失败!!!" + "\n订单详情:" + order);
			// e.printStackTrace();
		}
	}

	public String createOrderInfo(Order order) {
		StringBuffer temp = new StringBuffer();
		temp.append("订单号:" + order.getoId() + "\n");
		temp.append("下单时间:" + new SimpleDateFormat("yyyy年MM月dd日hh时mm分").format(order.getCreateTime()) + "\n");
		for (OrderItem item : order.getItems()) {
			temp.append("商品名称:" + item.getProName() + " × " + item.getNum() + "件\n");
		}
		temp.append("收货人:" + order.getUser().getName() + "\n");
		DeliveryAddress address = order.getAddress();
		temp.append("收货地址:" + address.getProvince() + address.getCity() + address.getArea() + "\n");
		temp.append("具体地址:" + address.getAddress() + "\n");
		temp.append("联系电话:" + address.getPhone() + "\n");
		temp.append("备注:" + order.getMsg() + "\n");
		temp.append("支付方式:");
		if(order.getPayWay()==PayWay.WEXIN_PAY){
			temp.append("微信支付\n");
		}else if(order.getPayWay()==PayWay.TICKET){
			temp.append("线下水票支付\n");
		}else{
			temp.append("电子票支付\n");
		}
		temp.append("支付状态:" + (order.getStatus() == PayStatus.PAY_DONE ? "支付完成" : "尚未支付") + "\n");
		temp.append("总计:" + order.getSummary() + "元\n");
		temp.append("下单用户:"+order.getUser().getName()+"\n");
		temp.append("下单用户联系方式:"+order.getUser().getPhone()+"\n");
		
//		temp.append("-------------------------------------------------------------");
		return temp.toString();
	}

}

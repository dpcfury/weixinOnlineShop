package com.slf.wx.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.slf.wx.email.constants.EmailConstants;
import com.slf.wx.email.service.EmailService;
import com.slf.wx.order.constants.OrderNotifyStatus;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.entity.OrderItem;
import com.slf.wx.order.service.OrderService;
import com.slf.wx.user.entity.DeliveryAddress;

/**
 * Description:定时任务 检测有没有未送达的邮件订单通知 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年8月8日
 * 
 */

public class CheckEmail {

	private static final Logger logger = LogManager.getLogger(CheckEmail.class);

	@Resource
	private JavaMailSender javaMailSender;

	@Resource
	private EmailService emailService;

	@Resource
	private OrderService orderService;

	// 将一天的订单进行汇总发送 防止有未发送的订单
	public void checkUnSentOrders() {
		List<Order> orders = orderService.getUnSentOrders(new Date());
		orders.addAll(orderService.getTicketOrdersNotSend(new Date()));
		if (orders.size() > 0) {
			try {
				sendEmail(orders);
				// 更新订单状态
				updateOrders(orders);
				logger.info("定时任务中发送未及时通知订单消息成功 总共发送给了 "+orders.size()+"条订单");
			} catch (Exception e) {
				logger.error("定时任务中发送未通知订单邮件失败:"+e.getMessage());
			}
		} else {
			logger.info("定时任务中近日未发送邮件订单为0条");
		}

	}

	private void updateOrders(List<Order> orders) {
		for (Order order : orders) {
			order.setNotifyFlag(OrderNotifyStatus.NOTIFY_DONE);
			orderService.updateOrder(order);
		}
	}

	private void sendEmail(List<Order> orders) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(EmailConstants.mailFrom);
			helper.setTo(EmailConstants.mailTo);
			helper.setSubject("截止夜间12点为止未成功发送的订单信息:");
			helper.setSentDate(new Date());
			// message.setContent(createOrderInfo(order),"text/plain");;
			helper.setText(createOrderInfo(orders));
			javaMailSender.send(message);
			logger.info("未提醒订单邮件发送成功");
		} catch (MessagingException e) {
			logger.error("未提醒订单邮件发送失败\n" + e.getMessage());
			// e.printStackTrace();
		}
	}

	public String createOrderInfo(List<Order> orders) {
		StringBuffer temp = new StringBuffer();
		for (Order order : orders) {
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
			temp.append("支付方式:" + (order.getPayWay() == 1 ? "微信支付" : "水票") + "\n");
			temp.append("支付状态:" + (order.getStatus() == 1 ? "支付完成" : "尚未支付") + "\n");
			temp.append("总计:" + order.getSummary() + "元\n");
			temp.append("下单用户:" + order.getUser().getName() + "\n");
			temp.append("下单用户联系方式:" + order.getUser().getPhone() + "\n\n");

			 temp.append("-------------------------------------------------------------\n");
		}

		return temp.toString();
	}
}

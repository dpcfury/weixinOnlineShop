package com.slf.wx.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.email.service.EmailService;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.service.OrderService;

/**
 * Description:测试邮件服务接口功能 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月28日
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestEmailService {
	
	@Resource
	private EmailService emailService;
	
	@Resource
	private OrderService orderService;
	

//	@Test
	public void testSendToCop(){
		Order order =orderService.getOrderById("402881e755dcde080155dcde9be40000");
		emailService.sendOrderToCop(order);
	}
}

package com.slf.wx.email.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slf.wx.email.service.EmailService;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.service.OrderService;

@Controller
@RequestMapping(value="/mail")
public class MailController {
	
	@Resource
	private EmailService emailService;
	
	@Resource
	private OrderService orderService;

//	@RequestMapping(value="/test")
	public void testEmail(){
		Order order =orderService.getOrderById("402881e755dcde080155dcde9be40000");
		emailService.sendOrderToCop(order);
	}
}

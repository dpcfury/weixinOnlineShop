package com.slf.wx.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slf.wx.order.constants.OrderQueryKind;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.service.OrderService;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.IpTools;

/**
 * Description:负责订单处理的Controller Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月11日
 * 
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Resource
	private OrderService orderService;

	@Resource
	private UserService userService;

	private static final Logger logger = LogManager.getLogger(OrderController.class);

	@RequestMapping(value = "/begin") // 跳转到下单的页面
	public String beginOrder() {
		return "order/order";
	}

	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Order createOrder(Order order, String addressId, String userId, HttpServletRequest request) {// items的传递问题
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		logger.info("ip:" + ip + "发起了一创建订单的请求");

		// 业务逻辑
		order.setCreateTime(new Date(System.currentTimeMillis()));
		order.setStatus(0);
		User user = userService.getUserById(userId);
		order.setUser(user);
		order.setAddress(userService.getUserAddress(user, addressId));

		return orderService.addOrder(order);
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Order> getOrders(@RequestParam("kind") String kind, String userID, HttpSession session) {
		List<Order> orders = new ArrayList<Order>(0);
		// User user =(User)session.getAttribute("currentUser");
		logger.info("执行到查询订单信息");
		User user = userService.getUserById(userID);
		if (kind.equals(OrderQueryKind.QUERY_ALL) || kind.equals(OrderQueryKind.QUERY_SUCCESS)
				|| kind.equals(OrderQueryKind.QUERY_UNPAY)) {
			orders = orderService.getUserOrders(user, kind);
			//对订单按时间顺序进行排序
			sortOrders(orders);
			
			
//			if(orders.size()>10){
//				orders= orders.subList(0, 10);
//			}
			
			System.out.println("查询到的订单的数目为:"+orders.size());
		} else {
			logger.error("查询订单的请求不正确 此次请求类型为:" + kind);
		}

		return orders;
	}
	
	@RequestMapping(value="/toQuery")
	public String showOrder(){
		return "/order/show_orders";
	}

	@SuppressWarnings("unused")
	private void sortOrders(List<Order> orders){
		Collections.sort(orders, new Comparator<Order>(){

			@Override
			public int compare(Order orderOne, Order orderTwo) {
				// TODO Auto-generated method stub
				long time1=orderOne.getCreateTime().getTime();
				long time2=orderTwo.getCreateTime().getTime();
				if(time1>=time2){
					return -1;
				}else{
					return 1;
				}
				
			}
			
		});
	}
}

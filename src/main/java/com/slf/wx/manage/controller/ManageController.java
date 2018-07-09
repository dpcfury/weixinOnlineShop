package com.slf.wx.manage.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.slf.wx.manage.entity.CSStaff;
import com.slf.wx.manage.service.CSStaffService;
import com.slf.wx.order.constants.OrderConfirmFlag;
import com.slf.wx.order.constants.OrderQueryKind;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.service.OrderService;
import com.slf.wx.pay.constant.PayStatus;
import com.slf.wx.sms.service.SmsService;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.IpTools;
import com.slf.wx.util.Pagination;

/**
 * Description:供后台客服使用的url接口类 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年9月9日
 * 
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageController {

	private static final Logger logger = LogManager.getLogger(ManageController.class);

	@Resource
	private UserService userService;

	@Resource
	private OrderService orderService;

	@Resource(name = "staffService")
	private CSStaffService staffService;

	@Resource(name = "smsService")
	private SmsService smsService;

	@RequestMapping(value = "/login")
	public String toLogin() {
		return "/admin/login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String staffLogin(String userName, String password, HttpServletRequest request, HttpSession session) {
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		logger.info("从ip:" + ip + "以用户名为:" + userName + "发起了一次登录后台的请求");

		// 执行登录逻辑
		CSStaff staff = staffService.authenticate(userName, password);
		System.out.println("username:" + userName);
		System.out.println("password:" + password);
		if (staff == null) {// 用户不存在
			return "login_error";
		} else {// 登录成功
			logger.info("客服:" + userName + "登录成功,登录时间:" + new SimpleDateFormat("yyyy年MM月dd日hh时mm分").format(new Date())
					+ "登录IP:" + ip);
			// 将当前客服人员存入session
			session.setAttribute("currentStaff", staff);
			return "login_ok";
		}

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		CSStaff staff = (CSStaff) session.getAttribute("currentStaff");
		logger.info(
				"客服:" + staff.getrName() + "退出登录状态,时间:" + new SimpleDateFormat("yyyy年MM月dd日hh时mm分").format(new Date()));

		session.removeAttribute("currentStaff");

		return "/admin/login";
	}

	// @RequestMapping(value="/orders")
	// public String showOrders(){
	// return "/admin/order_list";
	// }
	//
	// @RequestMapping(value="/orderlist",method=RequestMethod.GET)
	// @ResponseBody
	// @ResponseStatus(HttpStatus.OK)
	// public OrderList getOrder(){
	// List<Order> orders= orderService.getAllOrders();
	// sortOrders(orders);
	// return new OrderList(orders);
	// }

	@RequestMapping(value = "/order/{currentPage}/{pageSize}", method = RequestMethod.GET)
	public ModelAndView getOrders(@PathVariable("currentPage") int currentPage,
			@PathVariable("pageSize") int pageSize) {
		ModelAndView view = new ModelAndView("/admin/order_list");
		@SuppressWarnings("rawtypes")
		Pagination pagination = orderService.getOrders(currentPage, pageSize, true);
		view.addObject("order_cp", pagination);
		return view;
	}

	@RequestMapping(value = "/orderdetail/{orderId}")
	public ModelAndView getOrderDetail(@PathVariable("orderId") String orderId) {
		ModelAndView view = new ModelAndView("/admin/order_detail");
		Order order = orderService.getOrderById(orderId);
		view.addObject("currentOrder", order);
		return view;
	}

	@RequestMapping(value = "/order/confirm/{orderId}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String confirmOrder(@PathVariable("orderId") String orderId) {
		Order order = orderService.getOrderById(orderId);
		try {
			// 更新订单确认就收标志位
			order.setConfirmFlag(OrderConfirmFlag.ORDER_CONFIRMED);
			orderService.updateOrder(order);

			// 发送短信通知到客户
			if (smsService.send(order.getUser().getPhone(), order.getUser().getName(), order.getoId())) {
				logger.info("订单确认短信提示发出成功,订单号:" + order.getoId());
			} else {
				logger.info("订单确认短信提示发出失败,订单号:" + order.getoId());
			}

		} catch (Exception e) {
			logger.info("客服确认订单时跟新出错，出错订单号:" + order.getoId());
		}

		return "OK";
	}

	@RequestMapping(value = "/order/status/{orderId}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String updatePayStatus(@PathVariable("orderId") String orderId) {
		Order order = orderService.getOrderById(orderId);
		try {
			// 更新订单支付位
			order.setStatus(PayStatus.PAY_DONE);
			orderService.updateOrder(order);
		} catch (Exception e) {
			logger.info("客服确认水票支付订单出错，出错订单号:" + order.getoId());
		}
		return "OK";
	}

	@RequestMapping(value = "/user/{currentPage}/{pageSize}", method = RequestMethod.GET)
	public ModelAndView getUsers(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
		ModelAndView view = new ModelAndView("/admin/user_list");
		@SuppressWarnings("rawtypes")
		Pagination pagination = userService.getUsers(currentPage, pageSize, true);
		view.addObject("user_cp", pagination);
		return view;
	}

	@RequestMapping(value = "/userdetail/{userId}")
	public ModelAndView getUserDetail(@PathVariable("userId") String userId, HttpServletRequest request,
			HttpSession session) {
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		CSStaff staff = (CSStaff) session.getAttribute("currentStaff");
		logger.info("从ip:" + ip + "以用户名为:" + staff.getrName() + "发起了一次查看用户:" + userId + "的请求");

		// 业务逻辑
		ModelAndView view = new ModelAndView("/admin/user_detail");
		User user = userService.getUserById(userId);
		List<Order> orders = orderService.getUserOrders(user, OrderQueryKind.QUERY_ALL);
		view.addObject("user_detail", user);
		view.addObject("user_orders", orders);

		return view;
	}

	@RequestMapping(value = "/user/updateTicket/{userId}", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String updateUserTickets(@PathVariable("userId") String userId, int num, HttpServletRequest request,
			HttpSession session) {
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		CSStaff staff = (CSStaff) session.getAttribute("currentStaff");
		logger.info("从ip:" + ip + "以用户名为:" + staff.getrName() + "发起了一次修改用户:" + userId + "剩余电子票的请求");

		//
		User user = userService.getUserById(userId);
		user.setTickets_left(user.getTickets_left() + num);
		user.setTickets_total(user.getTickets_total() + num);
		try {
			userService.updateUser(user);
			logger.info("成功为用户"+user.getName()+"添加了"+num+"张电子水票");
			
			return "OK";
		} catch (Exception e) {
			logger.info("为用户:"+user.getName()+"添加水票失败");
			return "FAILED";
		}

	}

	private void sortOrders(List<Order> orders) {
		Collections.sort(orders, new Comparator<Order>() {

			@Override
			public int compare(Order orderOne, Order orderTwo) {
				// TODO Auto-generated method stub
				long time1 = orderOne.getCreateTime().getTime();
				long time2 = orderTwo.getCreateTime().getTime();
				if (time1 >= time2) {
					return -1;
				} else {
					return 1;
				}

			}

		});
	}

}

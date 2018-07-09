package com.slf.wx.order.service;

import java.util.Date;
import java.util.List;

import com.slf.wx.order.entity.Order;
import com.slf.wx.user.entity.User;
import com.slf.wx.util.Pagination;

public interface OrderService {

	//持久化一个新的订单，如果成果就返回带有id的订单对象
	public Order addOrder(Order order);
	
	//通过订单Id获得一个订单对象
	public Order getOrderById(String id);
	
	//更新一个Order对象
	public boolean updateOrder(Order order);
	
	//按日期得到当天的未送达的订单对象
	public List<Order> getUnSentOrders(Date date);
	
	//获得当天的未发送出邮件的订单通知
	public List<Order> getTicketOrdersNotSend(Date date);
	
	//根据用户要求查看其下的订单
	public List<Order> getUserOrders(User User,String kind);
	
	public List<Order> getAllOrders();
	
	//带分页获得一页订单信息
	public Pagination getOrders(int currentPage,int pageSize,boolean desc);
	
	public int getTotalCount();
}

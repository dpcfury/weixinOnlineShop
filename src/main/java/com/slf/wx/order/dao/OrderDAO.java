package com.slf.wx.order.dao;

import java.util.Date;
import java.util.List;

import com.slf.wx.dao.BaseDao;
import com.slf.wx.order.entity.Order;
import com.slf.wx.user.entity.User;

public interface OrderDAO extends BaseDao<Order,String>{

	public List<Order> getUnSentOrders(Date fromDate,Date endDate);
	
	public List<Order> getUnSentTicOrders(Date fromDate,Date endDate);
	
	public List<Order> getOrders(User user  ,String kind);
	
}

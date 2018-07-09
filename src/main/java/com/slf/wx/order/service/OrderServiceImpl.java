package com.slf.wx.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.slf.wx.order.dao.OrderDAO;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.entity.OrderPagination;
import com.slf.wx.service.BaseService;
import com.slf.wx.user.entity.User;
import com.slf.wx.util.Pagination;

@Service("orderService")
@Transactional
public class OrderServiceImpl extends BaseService implements OrderService {

	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Resource
	private OrderDAO orderDAO;

	@Override
	public Order addOrder(Order order) {
		validate(order);
		String id = orderDAO.save(order);
		return orderDAO.find(id);
	}

	@Override
	public Order getOrderById(String id) {
		return orderDAO.find(id);
	}

	@Override
	public boolean updateOrder(Order order) {
		boolean result = false;
		try {
			orderDAO.update(order);
			result = true;
		} catch (Exception e) {
			logger.error("更新订单失败:" + order.getoId() + "\n" + e.getMessage());
		}
		return result;

	}

	@Override
	public List<Order> getUnSentOrders(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = format.format(date);
		String year = str.split(" ")[0];
		String from = year + " 00:00:00";
		String end = year + "  24:00:00";
		System.out.println(from+"\n"+end);
		Date fromDate = null;
		Date endDate = null;
		try {
			fromDate = (Date) format.parse(from);
			endDate = (Date) format.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDAO.getUnSentOrders(fromDate, endDate);

	}


	@Override
	public List<Order> getUserOrders(User user, String kind) {
		return orderDAO.getOrders(user , kind);
		
	}

	@Override
	public List<Order> getTicketOrdersNotSend(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = format.format(date);
		String year = str.split(" ")[0];
		String from = year + " 00:00:00";
		String end = year + "  24:00:00";
		System.out.println(from+"\n"+end);
		Date fromDate = null;
		Date endDate = null;
		try {
			fromDate = (Date) format.parse(from);
			endDate = (Date) format.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDAO.getUnSentTicOrders(fromDate, endDate);
		
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderDAO.getAll();
		
	}


	@Override
	public int getTotalCount() {
		return orderDAO.getTotalCount();
		
	}

	@Override
	public Pagination getOrders(int currentPage, int pageSize, boolean desc) {
		int totalCount = getTotalCount();
		Pagination pagination = new OrderPagination(currentPage, pageSize,
				totalCount);
		List<Order> orders =orderDAO.list(currentPage, pageSize, desc);
		if (orders.size() == 0) {
			logger.info("系统中还未有第一条订单信息");
			pagination.setTotalPages(0);
		} else {
			if (totalCount % pageSize == 0) {
				pagination.setTotalPages(totalCount / pageSize );
			} else {
				pagination.setTotalPages(totalCount / pageSize+1 );
			}
			
		}
		
		pagination.setItems(orders);
		return pagination;
		
	}

	
	
}

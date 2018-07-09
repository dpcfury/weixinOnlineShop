package com.slf.wx.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.order.constants.OrderQueryKind;
import com.slf.wx.order.entity.Order;
import com.slf.wx.order.entity.OrderItem;
import com.slf.wx.order.service.OrderService;
import com.slf.wx.order.util.OIDGenerator;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.Pagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestOrderService {

	@Resource
	private OrderService orderService;
	
	@Resource
	private UserService userService;
	
//	@Test
	public void testAddOrder(){
		Order order =new Order();
		
		order.setCreateTime(new Date(System.currentTimeMillis()));
		order.setoId(OIDGenerator.createOID());
		order.setUser(userService.getUserById("0001"));
		OrderItem item =new OrderItem();
		item.setProId("402881e755c940be0155c940c2ff0000");
		item.setProName("吉氧山泉");
		item.setNum(10);
		item.setProPrice(36.0);
		item.setItemSummary(item.getProPrice()*item.getNum());
		
		order.getItems().add(item);
//		order.setAddress(new DeliveryAddress(1,"江苏省","苏州市","高新区","15606135595","张先生","港隆城4幢1205"));
		order.setPayWay(1);
		order.setStatus(0);
		order.setSummary(36.0*2);
		System.out.println(orderService.addOrder(order));
	}
	
//	@Test
	public void testGetOrderById(){
		Order order= orderService.getOrderById("402881ec55d3a6880155d3a68dd30000");
		if(order!=null)
			System.out.println(order);
		else
			System.out.println("order为空"); 
			
	} 
	
	//@Test
	public void testGetUnSentOrders(){
		List<Order> orders=orderService.getUnSentOrders(new Date());
		System.out.println("size:"+orders.size());
		for(Order order:orders){
			System.out.println(order);
		}
	}
	
//	@Test
	public void testGetUserOrders(){
		User user =userService.getUserById("oM4JUuDmo_U_TsbdEj4AEetKHtB0");
		List<Order> orders=orderService.getUserOrders(user, OrderQueryKind.QUERY_UNPAY);
		for(Order order:orders){
			System.out.println(order);
		}
	}
	
	@Test
	public void testGetPageOrders(){
		Pagination pagination =orderService.getOrders(0, 10, true);
		System.out.println("pagesize:"+pagination.getPageSize());
		System.out.println("totalsize:"+pagination.getTotalCount());
		System.out.println("totalPage:"+pagination.getTotalPages());
		System.out.println("currentPage:"+pagination.getCurrentPage());
		Iterator<Order> it=pagination.getItems().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}

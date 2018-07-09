package com.slf.wx.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.user.entity.DeliveryAddress;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.exception.UserNotFoundException;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.Pagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestUserService {

	@Resource
	private UserService userService;

	//@Test
	public void testAddUser() {
		User user = new User();
		user.setId("0001");
		user.setName("dpc");
		user.setOther_resident("yeye nainai baba mama ");
		// user.setLevel(1);
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setTickets_left(21);
		user.setTickets_total(150);
		user.setRegister_time(new Date(System.currentTimeMillis()));
		user.setPhone("18896583964");
		user.setAddress("haa");
		user.setProvince("江苏省");
		user.setCity("苏州市");
		user.setArea("高新区");

		System.out.println(userService.addUser(user));

	}

//	 @Test
	public void testGetUserById() {
		System.out.println(userService.getUserById("0001"));
	}

	@Test
	public void testDeleteUser() {

		try{
			userService.deleteUser("oM4JUuLAZHSfKMfXR63RaX_WrXl0");}
		catch(UserNotFoundException e){
			System.out.println(e.getDeveloperMessage());
		}
		System.out.println("删除成功");
	}

//	@Test
	public void testGetTotalCount(){
		System.out.println(userService.getTotalNum());
	}

//	@Test
	public void testUpdateUser(){
		User user =userService.getUserById("0001");
		user.setAddress("中华园大酒店");
		if(userService.updateUser(user)){
			System.out.println("跟新成功");
		}else{
			System.out.println("更新失败");
		}
	}
	
//	@SuppressWarnings("rawtypes")
////	@Test
//	public void testPagination(){
//		Pagination pg=userService.getUsers(0, 2);
//		System.out.println("当前页码:"+pg.getCurrentPage());
//		System.out.println("总共条数:"+pg.getTotalCount());
//		System.out.println("数据为:"+pg.getItems());
//	}
	
//	@Test
	public void testAddDeliveryAddress(){
		User user =userService.getUserById("0001");
		DeliveryAddress address=new DeliveryAddress();
		address.setProvince("江苏省");
		address.setCity("苏州市");
		address.setArea("高新区");
		address.setName("张先生");
		address.setAddress("港隆城4幢1205");
		address.setPhone("15606135595");	
		userService.addDeliveryAddress(user, address);
	}
	
}

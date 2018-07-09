package com.slf.wx.user.service;

import com.slf.wx.user.entity.DeliveryAddress;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.entity.UserPagination;
import com.slf.wx.util.Pagination;

/**
*	Description:提供用户信息类的管理服务
*	Company:苏州市咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月6日
 *  
 */
public interface UserService {
	
	//添加一个User对象
	public User addUser(User user);

	//根据唯一的用户标示获得User对象
	public User getUserById(String id);
	
	//根据id删除一个User对象
	public boolean  deleteUser(String id);
	
	//更新一个user对象
	public boolean updateUser(User user);
	
	//获得所有用户总数
	public int getTotalNum();
	
	//带分页获得一页订单信息
	public Pagination getUsers(int currentPage,int pageSize,boolean desc);
	
	
	//为user对象添加收货地址
	public void addDeliveryAddress(User user ,DeliveryAddress newAddress);
	
	public DeliveryAddress getUserAddress(User user,String addressId);
	
	
	
}

package com.slf.wx.user.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.slf.wx.service.BaseService;
import com.slf.wx.user.dao.UserDAO;
import com.slf.wx.user.entity.DeliveryAddress;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.entity.UserPagination;
import com.slf.wx.user.exception.UserNotFoundException;
import com.slf.wx.util.Pagination;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService{
	private static final Logger logger = LogManager
			.getLogger(UserServiceImpl.class);
	
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	
	@Override
	public User getUserById(String id) {
		
		return userDAO.find(id);
	}


	@Override
	public User addUser(User user) {
		validate(user);
		String id=userDAO.save(user);
		return userDAO.find(id);
	}


	@Override
	public boolean deleteUser(String id) {
		boolean result =false;
		User user =userDAO.find(id);
		if(user == null){
			//抛出异常
			throw new UserNotFoundException();
		}else{
			//执行删除逻辑
			userDAO.delete(user);
			result =true;
		}
		return result;
	}


	@Override
	public int getTotalNum() {
		return userDAO.getTotalCount();
	}


	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		try{
			userDAO.update(user);
			result=true;
		}catch(Exception e){
			logger.error("更新user失败");
		}
		return result;
	}


	


	@Override
	public void addDeliveryAddress(User user, DeliveryAddress newAddress) {
		user.getDeliveryAddresses().add(newAddress);
		if(updateUser(user)){
			logger.info("时间："+new Date(System.currentTimeMillis())+" 添加收货地址成功");
		}else{
			logger.error("时间："+new Date(System.currentTimeMillis())+" 添加收货地址失败");
		}
	}


	@Override
	public DeliveryAddress getUserAddress(User user, String addressId) {
		for(DeliveryAddress temp :user.getDeliveryAddresses()){
			if(temp.getId().equals(addressId))
				return temp;
				
		}
		return null;
	}


	@Override
	public Pagination getUsers(int currentPage, int pageSize, boolean desc) {
		int totalCount = getTotalNum();
		Pagination pagination = new UserPagination(currentPage, pageSize,
				totalCount);
		List<User> users=userDAO.list(currentPage, pageSize, desc);
		if (users.size() == 0) {
			logger.info("系统中还未有用户注册");
			pagination.setTotalPages(0);
		} else {
			if (totalCount % pageSize == 0) {
				pagination.setTotalPages(totalCount / pageSize );
			} else {
				pagination.setTotalPages(totalCount / pageSize+1 );
			}
			
		}
		
		pagination.setItems(users);
		return pagination;
		
	}

	
}

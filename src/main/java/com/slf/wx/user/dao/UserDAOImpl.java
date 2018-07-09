package com.slf.wx.user.dao;

import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.user.entity.User;

@Repository(value="userDAO")
public class UserDAOImpl extends BaseDaoImpl<User,String> implements UserDAO{

}

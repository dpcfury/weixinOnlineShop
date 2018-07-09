package com.slf.wx.user.exception;

import com.slf.wx.exception.BaseWebApplicationException;

public class UserNotFoundException extends BaseWebApplicationException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super(400, "用户不存在", "请求操作一个不存在的用户");
	}

	
}

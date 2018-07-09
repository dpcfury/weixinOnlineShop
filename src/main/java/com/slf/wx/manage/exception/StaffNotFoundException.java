package com.slf.wx.manage.exception;

import com.slf.wx.exception.BaseWebApplicationException;

public class StaffNotFoundException extends BaseWebApplicationException{
	private static final long serialVersionUID = 1L;

	public StaffNotFoundException() {
		super(400, "用户不存在", "请求操作一个不存在于的客服");
	}
}

package com.slf.wx.order.exception;

import com.slf.wx.exception.BaseWebApplicationException;

@SuppressWarnings("serial")
public class OrderNotFoundException extends BaseWebApplicationException{

	public OrderNotFoundException(){
		super(400,"订单不存在","试图操作一个不存在的订单");
	}
}

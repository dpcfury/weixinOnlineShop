package com.slf.wx.product.exception;

import com.slf.wx.exception.BaseWebApplicationException;

public class ProductNotFoundException extends BaseWebApplicationException{

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(){
		super(400,"产品不存在","试图查看一个不存在的产品信息");
	}
}

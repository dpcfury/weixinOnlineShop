package com.slf.wx.order.entity;

import com.slf.wx.util.Pagination;

public class OrderPagination extends Pagination<Order>{

	public OrderPagination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderPagination(int currentPage, int pageSize, int totalCount) {
		super(currentPage, pageSize, totalCount);
	}
	
	
	
}

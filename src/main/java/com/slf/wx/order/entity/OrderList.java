package com.slf.wx.order.entity;

import java.util.List;

public class OrderList {
	private List<Order> data;

	public List<Order> getData() {
		return data;
	}

	public void setData(List<Order> data) {
		this.data = data;
	}

	public OrderList(List<Order> data) {
		super();
		this.data = data;
	}

	public OrderList() {
		super();
	}

	@Override
	public String toString() {
		return "OrderList [data=" + data + "]";
	}
	
	
}

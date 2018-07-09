package com.slf.wx.user.entity;

import com.slf.wx.util.Pagination;

public class UserPagination extends Pagination<User> {

	public UserPagination(int currentPage, int pageSize, int totalCount){
		super(currentPage,pageSize,totalCount);
	}
}

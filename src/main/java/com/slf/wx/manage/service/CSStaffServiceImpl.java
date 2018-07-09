package com.slf.wx.manage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slf.wx.manage.dao.CSStaffDAO;
import com.slf.wx.manage.entity.CSStaff;
import com.slf.wx.service.BaseService;

@Service("staffService")
@Transactional
public class CSStaffServiceImpl extends BaseService implements CSStaffService{
	
	@Resource
	private CSStaffDAO cSStaffDAO;

	@Override
	public CSStaff authenticate(String sName, String password) {
		CSStaff staff=cSStaffDAO.getStaffByUserNamePassword(sName, password);
		return staff;
		
	}

}

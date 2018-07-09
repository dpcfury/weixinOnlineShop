package com.slf.wx.manage.dao;

import com.slf.wx.dao.BaseDao;
import com.slf.wx.manage.entity.CSStaff;

/**
*	Description:客服人员的DAO接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年9月9日
 * 
 */
public interface CSStaffDAO extends BaseDao<CSStaff, String>{

	public  CSStaff getStaffByUserNamePassword(String userName,String password);
}

package com.slf.wx.manage.service;

import com.slf.wx.manage.entity.CSStaff;

/**
*	Description:客服人员实体类相关的服务接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年9月9日
 * 
 */
public interface CSStaffService {

	//通过用户名和密码验证客服人员
	public CSStaff authenticate(String sName,String password);
}

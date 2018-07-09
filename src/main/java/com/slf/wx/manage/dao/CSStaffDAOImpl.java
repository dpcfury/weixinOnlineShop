package com.slf.wx.manage.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.manage.entity.CSStaff;

/**
*	Description:客服人员实体类的底层DAO实现
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年9月9日
 * 
 */
@Repository("cSStaffDAO")
public class CSStaffDAOImpl extends BaseDaoImpl<CSStaff, String> implements CSStaffDAO{

	
	@Override
	public CSStaff getStaffByUserNamePassword(String userName, String password) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("sName", userName)).add(Restrictions.eq("password", password));
		return (CSStaff)criteria.uniqueResult();
	}

	
	
}

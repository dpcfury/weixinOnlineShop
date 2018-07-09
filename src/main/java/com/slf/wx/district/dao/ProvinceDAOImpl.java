package com.slf.wx.district.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.district.entity.Province;

@Repository("provinceDAO")
public class ProvinceDAOImpl extends BaseDaoImpl<Province,	Integer> implements ProvinceDAO{

	@Override
	public Province getProvinceByPId(String pId) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("provinceId", pId));
		return (Province) criteria.uniqueResult();
		
	}



}

package com.slf.wx.district.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.district.entity.City;

@Repository("cityDAO")
public class CityDAOImpl extends BaseDaoImpl<City,Integer> implements CityDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCitesByProvinceId(String provinceId) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("provinceId", provinceId));
		return (List<City>)criteria.list();
	}

	@Override
	public City getCityByCId(String cID) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("cityId", cID));
		return (City)criteria.uniqueResult();
		
	}

}

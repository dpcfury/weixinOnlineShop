package com.slf.wx.district.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slf.wx.dao.BaseDaoImpl;
import com.slf.wx.district.entity.Area;

@Repository("areaDAO")
public class AreaDAOImpl extends BaseDaoImpl<Area, Integer> implements AreaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAreasByCityId(String cityId) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("cityId", cityId));
		return (List<Area>)criteria.list();
		
	}

	@Override
	public Area getAreaByAId(String aId) {
		Criteria criteria =createCriteria();
		criteria.add(Restrictions.eq("areaId", aId));
		return (Area)criteria.uniqueResult();
		
	}

}

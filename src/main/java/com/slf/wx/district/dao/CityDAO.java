package com.slf.wx.district.dao;

import java.util.List;

import com.slf.wx.dao.BaseDao;
import com.slf.wx.district.entity.City;

public interface CityDAO extends BaseDao<City,Integer>{

	public List<City> getCitesByProvinceId(String provinceId);
	
	public City getCityByCId(String cID);
}

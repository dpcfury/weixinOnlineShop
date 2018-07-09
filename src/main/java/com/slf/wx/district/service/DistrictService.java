package com.slf.wx.district.service;

import java.util.List;

import com.slf.wx.district.entity.Area;
import com.slf.wx.district.entity.City;
import com.slf.wx.district.entity.Province;

public interface DistrictService {

	public List<Province> getProvinces();
	
	public List<City> getCities(String provinceId);
	
	public List<Area> getAreas(String cityId);
	
	public Province getProvinceByPId(String pId);
	
	public City getCityByCId(String cId);
	
	public Area getAreaByAId(String aId);
}

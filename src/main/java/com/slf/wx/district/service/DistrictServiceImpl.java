package com.slf.wx.district.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.slf.wx.district.dao.AreaDAO;
import com.slf.wx.district.dao.CityDAO;
import com.slf.wx.district.dao.ProvinceDAO;
import com.slf.wx.district.entity.Area;
import com.slf.wx.district.entity.City;
import com.slf.wx.district.entity.Province;
import com.slf.wx.service.BaseService;

@Service
@Transactional
public class DistrictServiceImpl extends BaseService implements DistrictService {

	@Resource
	private ProvinceDAO provinceDAO;
	
	@Resource
	private CityDAO cityDAO;
	
	@Resource
	private AreaDAO areaDAO;

	@Override
	public List<Province> getProvinces() {
		return provinceDAO.getAll();

	}

	@Override
	public List<City> getCities(String provinceId) {
		return cityDAO.getCitesByProvinceId(provinceId);

	}

	@Override
	public List<Area> getAreas(String cityId) {
		return areaDAO.getAreasByCityId(cityId);

	}

	@Override
	public Province getProvinceByPId(String pId) {
		return provinceDAO.getProvinceByPId(pId);
		
	}

	@Override
	public City getCityByCId(String cId) {
		return cityDAO.getCityByCId(cId);
		
	}

	@Override
	public Area getAreaByAId(String aId) {
		return areaDAO.getAreaByAId(aId);
		
	}

}

package com.slf.wx.district.dao;

import java.util.List;

import com.slf.wx.dao.BaseDao;
import com.slf.wx.district.entity.Area;

public interface AreaDAO extends BaseDao<Area,Integer>{

	public List<Area> getAreasByCityId(String cityId);
	
	public Area getAreaByAId(String aId);
}

package com.slf.wx.district.dao;

import com.slf.wx.dao.BaseDao;
import com.slf.wx.district.entity.Province;

public interface ProvinceDAO extends BaseDao<Province,Integer> {

	public Province getProvinceByPId(String pId);
}

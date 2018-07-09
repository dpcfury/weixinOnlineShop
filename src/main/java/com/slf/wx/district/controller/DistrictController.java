package com.slf.wx.district.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slf.wx.district.entity.Area;
import com.slf.wx.district.entity.City;
import com.slf.wx.district.entity.Province;
import com.slf.wx.district.service.DistrictService;

/**
*	Description:负责处理省市区三级联动信息服务的控制器
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月15日
 * 
 */
@Controller
@RequestMapping(value="/district")
public class DistrictController {
	
	@Resource
	private DistrictService districtService;
	
	@RequestMapping(value="/getProvinces",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Province> getProvinces(){
		return districtService.getProvinces();
	}
	
	@RequestMapping(value="/getCities",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<City> getCitites(@RequestParam("provinceId")String provinceId){
		List<City> cities= districtService.getCities(provinceId);
		return cities;
	}

	@RequestMapping(value="/getAreas",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Area> getAreas(String cityId){
		return districtService.getAreas(cityId);
	}
}

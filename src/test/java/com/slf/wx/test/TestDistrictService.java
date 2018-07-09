package com.slf.wx.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.district.entity.Area;
import com.slf.wx.district.entity.City;
import com.slf.wx.district.entity.Province;
import com.slf.wx.district.service.DistrictService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestDistrictService {

	@Resource
	private DistrictService districtService;

//	@Test
	public void getProvinces(){
		List<Province> provinces =districtService.getProvinces();
		for(Province pro:provinces){
			System.out.println(pro);
		}
	}
	
//	@Test
	public void testGetCities(){
		List<City> cities =districtService.getCities("320000");
		for(City city:cities){
			System.out.println(city);
		}
		
	}
	
//	@Test
	public void testGetAreas(){
		List<Area> areas =districtService.getAreas("320500");
		for(Area area:areas){
			System.out.println(area);
		}
	}
	
//	@Test
	public void testGerProvinceByPId(){
		Province pro =districtService.getProvinceByPId("110000");
		if(pro!=null){
			System.out.println("pro:"+pro);
		}else{
			System.out.println("空");
		}
	}
}

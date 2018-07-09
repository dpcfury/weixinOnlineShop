package com.slf.wx.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.manage.entity.CSStaff;
import com.slf.wx.manage.service.CSStaffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestStaffService {

	@Resource(name="staffService")
	private CSStaffService staffService; 
	
	@Test
	public void testAuthenticate(){
		CSStaff staff =staffService.authenticate("huangyan", "slf123");
		if(staff!=null){
			System.out.println("staff:"+staff);
		}else{
			System.out.println("用户名或密码错误");
		}
	}
}

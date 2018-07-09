package com.slf.wx.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.sms.service.SmsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestSMS {
	
	@Resource(name="smsService")
	private SmsService smsService;

	@Test
	public void testSendMsg(){
		if( smsService.send("15606135595","杜鹏程","20160824185415116376")){
			System.out.println("发送成功");
		}else{
			System.out.println("发送失败");
		}
	}
}

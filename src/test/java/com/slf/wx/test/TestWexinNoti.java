package com.slf.wx.test;

import org.junit.Test;

public class TestWexinNoti {

//	@Test
	public void testSS(){
		System.out.println(notifyReturnXML("SUCCESS",""));
	}
	
	public  String notifyReturnXML(String return_code, String return_msg) {
		return "<xml><return_code><![DATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	} 
}

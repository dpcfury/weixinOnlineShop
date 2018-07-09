package com.slf.wx.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.slf.wx.pay.constant.GzhaoConfig;

public class TestAuth {

	// @Test
	public void testUrlEncode() {
		try {
			System.out.println(URLEncoder.encode(GzhaoConfig.REDIRECT_URI, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 @Test
	public void testGetUri() {
		String auth_uri = null;
		try {
			auth_uri = String.format(GzhaoConfig.AUTH_URI, GzhaoConfig.APPID,
					URLEncoder.encode(GzhaoConfig.REDIRECT_URI, "utf-8"), GzhaoConfig.RESPONSE_TYPE,
					GzhaoConfig.AUTH_SCOPE, GzhaoConfig.AUTH_STATE);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("redirect:" + auth_uri);
	}

//	@Test
	public void testGetAccessTokenUri() {
		String auth_uri = null;
			auth_uri = String.format(GzhaoConfig.ACCESS_TOKEN_URI,GzhaoConfig.APPID,GzhaoConfig.APPSECRET,"r3Yoeaz1noL6gpJeshMPwEXL5Pj3");
		System.out.println(auth_uri);
	}
}

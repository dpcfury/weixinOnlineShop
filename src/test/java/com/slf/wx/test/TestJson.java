package com.slf.wx.test;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slf.wx.pay.util.WxSign;
import com.slf.wx.user.entity.User;

/**
*	Description:测试json的相关解析工具（jackson和gson）测试包括序列化对象和反序列化对象
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月13日
 * 
 */
public class TestJson {

//	@Test
	public void testDeserialize() throws IOException{
		ObjectMapper mapper= new ObjectMapper();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/slf_online/user/0001.json");
		HttpResponse response = null;
		User user =null;
		try {

			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				String temp=EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				System.out.println(temp);
				user =mapper.readValue(temp, User.class) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		
		System.out.println("反序列化得到的User信息为:"+user.getId()+"\n"+user.getAddress());
	}
	
	@Test
	public void testSerialize() throws JsonProcessingException{
		ObjectMapper mapper= new ObjectMapper();
		SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
        signMap.put("appId","wx2421b1c4370ec43b" ); 
        signMap.put("timeStamp", "1395712654");
        signMap.put("nonceStr", "e61463f8efa94090b1f366cccfbbb444");
        signMap.put("packageValue", "prepay_id="+"u802345jgfjsdfgsdg888");
        signMap.put("signType", "MD5");
        String paySin = WxSign.createSign(signMap,"dpczuishuai");
        String result=mapper.writeValueAsString(signMap);
        System.out.println(result);
        
        
	}
}

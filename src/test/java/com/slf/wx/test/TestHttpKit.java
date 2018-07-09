package com.slf.wx.test;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.slf.wx.util.HttpclientUtil;

/**
 * Description:测试发送http请求 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月12日
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestHttpKit {
	
	@Resource(name="httpClient")
	private CloseableHttpClient httpclient;

	// @Test
	public void testHttp() {
		HttpclientUtil util = new HttpclientUtil();
		try {
			System.out.println(util.get("http://localhost:8080/slf_online/user/0001.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testHttpClient() throws IOException {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/slf_online/user/0001.json");
		// httpGet.setHeader("contentType", "text/xml");
		HttpResponse response = null;
		try {

			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println(EntityUtils.toString(response.getEntity(), Consts.UTF_8));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("httpClient未出现空");
		} finally {
			httpclient.close();
		}

	}

}

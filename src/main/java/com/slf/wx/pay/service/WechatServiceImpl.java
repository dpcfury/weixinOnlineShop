package com.slf.wx.pay.service;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
*	Description:微信服务接口的实现类
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年8月11日
 * 
 */
@Service
public class WechatServiceImpl implements WechatService{
	

	@Resource(name = "httpClient")
	private CloseableHttpClient httpclient;
	
	private static final Logger logger = LogManager.getLogger(WechatServiceImpl.class);
	
	@Override
	public String wexinOrder(String url, String xml) {
		HttpPost httpPost =new HttpPost(url);
		StringEntity orderEntity=new StringEntity(xml, ContentType.create("text/xml", "UTF-8"));
		httpPost.setEntity(orderEntity);
		HttpResponse response = null;
		String result=null;
		try {
			if(httpclient==null)
				System.out.println("其实是httpclient为空了");
			response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送微信统一下单请求出错!!");
		}
		return result;
	}

}

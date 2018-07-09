package com.slf.wx.sms.service;

import org.springframework.stereotype.Service;

import com.slf.wx.sms.constants.SmsConfig;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service("smsService")
public class SmsServiceImpl implements SmsService{

	@Override
	public boolean send(String recNum, String name, String orderId) {
		boolean result = false;
		TaobaoClient client = new DefaultTaobaoClient(SmsConfig.URL, SmsConfig.APPKEY, SmsConfig.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(SmsConfig.EXTEND);
		req.setSmsType(SmsConfig.SMSTYPE);
		req.setSmsFreeSignName(SmsConfig.SMSFREESIGNNAME);
		req.setSmsParamString("{\"name\":\""+name
				+ "\",\"orderId\":\""+orderId+"\"}");
		req.setRecNum(recNum);
		req.setSmsTemplateCode(SmsConfig.SMSTEMPLATECODE);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
			result =true;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

}

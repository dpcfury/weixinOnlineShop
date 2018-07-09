package com.slf.wx.sms.service;

/**
*	Description:短信服务类接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年9月13日
 * 
 */
public interface SmsService {

	//按照模板发送指定的消息到用户
	public boolean send(String recNum,String name,String orderId);
}

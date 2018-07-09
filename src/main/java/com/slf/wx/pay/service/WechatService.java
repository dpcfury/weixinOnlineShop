package com.slf.wx.pay.service;

/**
*	Description:提供微信相关服务的接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年8月11日
 * 
 */
public interface WechatService {

	public String wexinOrder(String url,String xml);
}

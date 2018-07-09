package com.slf.wx.email.service;

import com.slf.wx.order.entity.Order;

/**
*	Description:提供邮件服务的接口
*	Company:苏州咕噜信息科技有限公司
*	@author dpc
*	@date   2016年7月28日
 * 
 */
public interface EmailService {

	//发送订单给企业
	public void sendOrderToCop(Order order);
	
	
}

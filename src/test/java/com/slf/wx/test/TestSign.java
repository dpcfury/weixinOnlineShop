package com.slf.wx.test;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import com.slf.wx.pay.util.WxPaySendData;
import com.slf.wx.pay.util.WxSign;

/**
 * Description:测试签名算法 和随机数生成
 * Company:苏州咕噜信息科技有限公司 
 * @author dpc
 * @date 2016年7月13日
 * 
 */
public class TestSign {

//	@Test
	public void testSign() {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		WxPaySendData data = new WxPaySendData();
		data.setAppid("wx2421b1c4370ec43b");
		data.setAttach("支付测试");
		data.setBody("JSAPI支付测试");
		data.setMch_id("10000100");
		data.setNonce_str("1add1a30ac87aa2db72f57a2375d8fec");
		data.setNotify_url("http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php");
		data.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
		data.setOut_trade_no("1415659990");
		data.setSpbill_create_ip("14.23.150.211");
		data.setTotal_fee(1);
		data.setTrade_type("JSAPI");
		data.setTime_start(WxSign.dateFormat(new Date()));
		
		parameters.put("appid", data.getAppid());
		parameters.put("attach", data.getAttach());
		parameters.put("body", data.getBody());
		parameters.put("mch_id", data.getMch_id());
		parameters.put("nonce_str", data.getNonce_str());
		parameters.put("notify_url", data.getNotify_url());
		parameters.put("out_trade_no", data.getOut_trade_no());
		parameters.put("total_fee", data.getTotal_fee());
		parameters.put("trade_type", data.getTrade_type());
		parameters.put("spbill_create_ip", data.getSpbill_create_ip());
		parameters.put("openid", data.getOpenid());
		parameters.put("device_info", data.getDevice_info());
		parameters.put("time_start", data.getTime_start());
		
		String sign =WxSign.createSign(parameters, "dpczuishuai");
		System.out.println("生成的签名为:"+sign);
	}
	
//	@Test
	public void testNonceStr(){
		System.out.println("随机生成的字符串序列为:"+WxSign.getNonceStr());
	}
}

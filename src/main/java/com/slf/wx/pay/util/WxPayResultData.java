package com.slf.wx.pay.util;

/**
 * Description:代表微信支付统一订单接口调用后的回复数据 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月13日 格式描述： <xml> <return_code><![CDATA[SUCCESS]]></return_code>
 *       <return_msg><![CDATA[OK]]></return_msg>
 *       <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
 *       <mch_id><![CDATA[10000100]]></mch_id>
 *       <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
 *       <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
 *       <result_code><![CDATA[SUCCESS]]></result_code>
 *       <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
 *       <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
 */
public class WxPayResultData {
	private String return_code;//返回的状态吗 SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String return_msg;//返回信息，如非空，为错误原因签名失败参数格式校验错误
	
	//以下字段在return_code为SUCCESS的时候有返回
	private String appid;//调用接口提交的公众账号ID
	private String mch_id;//调用接口提交的商户号
	private String nonce_str;//微信返回的随机字符串
	private String sign;//微信返回的签名
	private String result_code;//SUCCESS/FAIL
	
	//以下字段在return_code 和result_code都为SUCCESS的时候有返回
	private String prepay_id;//微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	private String trade_type;//调用接口提交的交易类型

	
	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	@Override
	public String toString() {
		return "WxPayResultData [return_code=" + return_code + ", return_msg=" + return_msg + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", nonce_str=" + nonce_str + ", sign=" + sign + ", result_code=" + result_code
				+ ", prepay_id=" + prepay_id + ", trade_type=" + trade_type + "]";
	}

	
	
}

package com.slf.wx.pay.constant;

/**
 * Description:公众号的相关配置参数 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月12日
 * 
 */
public class GzhaoConfig {

	public static final String APPID = "wx3041464ac52bcee7";
	public static final String APPSECRET = "36b2e85aa113c6b8c069a1046543d683";
	public static final String AUTH_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";
	public static final String REDIRECT_URI = "http://www.nicewater-slf.com/slf_online/user/auth";
	public static final String RESPONSE_TYPE = "code";
	public static final String AUTH_SCOPE = "snsapi_base";
	public static final String AUTH_STATE = "slf_123";
	public static final String ACCESS_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	public static final String GTANT_TYPE = "authorization_code";

	public static final String UNIFIDEORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String DEVICE_INFO = "WEB";
	public static final String MCH_ID="1364909502";
	public static final String PAY_NOTIFY_URL="http://www.nicewater-slf.com/slf_online/wxPay/notify";
	public static final String TRADE_TYPE="JSAPI";
	public static final String WX_PAY_KEY="Jiangsushuilifangshuiyekeji20168";
}

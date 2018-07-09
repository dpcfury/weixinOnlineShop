package com.slf.wx.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slf.wx.district.service.DistrictService;
import com.slf.wx.order.constants.OrderQueryKind;
import com.slf.wx.pay.constant.GzhaoConfig;
import com.slf.wx.pay.util.AccessToken;
import com.slf.wx.user.entity.DeliveryAddress;
import com.slf.wx.user.entity.User;
import com.slf.wx.user.exception.UserNotFoundException;
import com.slf.wx.user.service.UserService;
import com.slf.wx.util.IpTools;

/**
 * Description:实现用户信息相关控制的Controller Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月6日
 * 
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private DistrictService districtService;

	@Resource(name = "httpClient")
	private CloseableHttpClient httpclient;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@RequestMapping(value = "/{id}.json", method = RequestMethod.GET)
	public @ResponseBody User getUserById(@PathVariable("id") String id, HttpServletRequest request) {
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		logger.info("ip:" + ip + "发起了一次查看用户" + id + "信息的请求");

		// 业务逻辑
		User user = userService.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			return user;
		}

	}

	// 跳转到登录页面(实质是静默授权页面)
	@RequestMapping(value = "/doAuth")
	public String doAuth(@RequestParam(required = true) String intent, @RequestParam(required=false)String from,HttpSession session) {
		// 在本地session中存入intent
		session.setAttribute("user_intent", intent);
		
		//本次session存入from （后续验证是否从二维码转来进行登录）
		session.setAttribute("user_from", from);
		
		String auth_uri = null;
		try {
			auth_uri = String.format(GzhaoConfig.AUTH_URI, GzhaoConfig.APPID,
					URLEncoder.encode(GzhaoConfig.REDIRECT_URI, "utf-8"), GzhaoConfig.RESPONSE_TYPE,
					GzhaoConfig.AUTH_SCOPE, GzhaoConfig.AUTH_STATE);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:" + auth_uri;
	}

	/**
	 * 供静默授权后的回调地址
	 * 需要先到公众平台官网中的开发者中心页配置授权回调域名。请注意，这里填写的是域名（是一个字符串），而不是URL，因此请勿加http://等协议头；
	 * 回调提供参数: 第一步：用户同意授权，获取code 第二步：通过code换取网页授权access_token
	 * 第三步：通过openid查找用户判断用户是否登录
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String authorize(String code, String state, HttpSession session) {
		if (code != null) {// code不是空代表用户接受了授权，则继续用code获得access_token逻辑
			// 拼凑请求access_token的uri
			String atu = null;
			atu = String.format(GzhaoConfig.ACCESS_TOKEN_URI, GzhaoConfig.APPID, GzhaoConfig.APPSECRET, code);

			// 获取access_token
			AccessToken accessToken = getAccessToken(atu);
			session.setAttribute("access_token", accessToken);
			System.out.println("access_token:" + accessToken);

			// 通过获得的openid进行用户查找判断是否已经注册过
			User user = userService.getUserById(accessToken.getOpenid());
			if (user == null) {// 即还未注册的情况,
				//如果from为空 表示不是正常的扫码注册
				if(session.getAttribute("user_from")==null){
					logger.info("非邀请扫码注册! 用户openid:"+accessToken.getOpenid());
					return "user/auth_error";
				}else{//如果from不空代表会员扫码进行的注册登录
					logger.info("用户:"+accessToken.getOpenid()+"正进行扫码注册");
					return "redirect:/user/doRegister";
				}
				
				
			} else {
				// 当前会话存入用户对象
				session.setAttribute("currentUser", user);
				String uIntent = (String) session.getAttribute("user_intent");
				if ("shop".equals(uIntent)) {
					// 用户已经注册过并且intent是shop接直接跳转到购物页面
					System.out.println("用户已经注册过，直接跳转到购物页面");
					return "redirect:/order/begin";
				} else if ("queryOrder".equals(uIntent)) {
					// 用户已经注册过并且intent是queryOrder就跳转到订单查看页面
					System.out.println("用户已经注册过，直接跳转到订单查询页面");
					return "redirect:/order/toQuery";
				}else{
					//操作违法
					return "user/auth_error";
				}
				

			}
		} else {
			logger.error("code为空 授权失败");
			session.setAttribute("auth_error", "授权失败!! 请在公众号内进行商城的浏览");
			return "user/auth_error";
		}
	}

	private AccessToken getAccessToken(String atu) {
		HttpGet httpGet = new HttpGet(atu);
		HttpResponse response = null;
		AccessToken accessToken = null;
		try {

			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				String temp = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				ObjectMapper mapper = new ObjectMapper();
				accessToken = mapper.readValue(temp, AccessToken.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求access_token出错");
		}
		return accessToken;

	}

	@RequestMapping(value = "/doRegister")
	public String doRegister() {
		return "user/register";
	}

	/**
	 * @param user
	 *            请求创建的user实体
	 * @param session
	 *            会话对象
	 * @return 对应的试图
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user, HttpSession session, HttpServletRequest request) {// 执行注册的请求方法
		// 安全日志记载
		String ip = IpTools.getIpAddr(request);
		logger.info("ip:" + ip + "发起了一次注册用户的请求");

		// 业务逻辑设置相关属性
		user.setRegister_time(new Date(System.currentTimeMillis()));
		AccessToken accessToken = (AccessToken) session.getAttribute("access_token");
		user.setId(accessToken.getOpenid());
		user.setLevel(1);
		// 设置省市区信息
		String pId = user.getProvince();
		String cId = user.getCity();
		String aId = user.getArea();
		user.setProvince(districtService.getProvinceByPId(pId).getProvinceName());
		user.setCity(districtService.getCityByCId(cId).getCityName());
		user.setArea(districtService.getAreaByAId(aId).getAreaName());

		// 为用户以家庭地址初始化一个收货地址
		DeliveryAddress defaultAddress = new DeliveryAddress();
		defaultAddress.setAddress(user.getAddress());
		defaultAddress.setProvince(user.getProvince());
		defaultAddress.setCity(user.getCity());
		defaultAddress.setArea(user.getArea());
		defaultAddress.setName(user.getName());
		defaultAddress.setPhone(user.getPhone());

		user.getDeliveryAddresses().add(defaultAddress);
		System.out.println("接受到的user对象信息为:" + user);

		try {
			User currentUser = userService.addUser(user);
			session.setAttribute("currentUser", currentUser);
		} catch (Exception e) {
			logger.error("用户注册失败:" + e.getMessage());
			return "/user/register_error";// 提示注册失败页面
		}
		return "/user/register_ok";// 反回提示页面
	}

	@RequestMapping(value = "/address/add")
	public String goAddAddress() {
		return "user/add_address";
	}

	@RequestMapping(value = "/address/addSubmit", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String addAddressForUser(DeliveryAddress address, HttpSession session) {
		// 获取到当前注册过或登录过的用户对象
		User currentUser = (User) session.getAttribute("currentUser");
		String pId = address.getProvince();
		String cId = address.getCity();
		String aId = address.getArea();
		address.setProvince(districtService.getProvinceByPId(pId).getProvinceName());
		address.setCity(districtService.getCityByCId(cId).getCityName());
		address.setArea(districtService.getAreaByAId(aId).getAreaName());

		currentUser.getDeliveryAddresses().add(address);
		try {
			userService.updateUser(currentUser);
			session.setAttribute("currentUser", currentUser);// 更新session中的当前用户对象
		} catch (Exception e) {
			logger.error("增加收货地址失败:" + e.getMessage());
		}

		return "redirect:/order/begin";
	}

	@RequestMapping(value = "/getDeliveryAddress", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Set<DeliveryAddress> getAddress(HttpSession session) {
		// 获取到当前注册过或登录过的用户对象
		User currentUser = (User) session.getAttribute("currentUser");
		return currentUser.getDeliveryAddresses();
	}
	
	//@RequestMapping(value="/center")
	public String userCenter(HttpSession session){
		User user =userService.getUserById("oM4JUuLAZHSfKMfXR63RaX_WrXl0");
		session.setAttribute("currentUser", user);
		return "/user/usercenter";
	}
	
	@RequestMapping(value="/info")
	public String userDetail(){
		return "/user/userInfo";
	}
}

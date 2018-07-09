package com.slf.wx.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.slf.wx.manage.controller.ManageController;

/**
 * Description:进行登录控制的拦截器 对某些请求在未登录的情况下直接进行拦截 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月27日
 * 
 */
public class SignInInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LogManager.getLogger(SignInInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView view)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 对请求进行拦截 检查session中是否有当前用户对象 有表示开启了会话 没有表示没有
	// 没有当前用户对象的请求直接进行拦截终止请求，其他情况直接放过
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		logger.info("用户特殊请求操作安全拦截:");
		if (request.getSession().getAttribute("currentUser")==null) {
			logger.info("还未登录授权,专向登录授权");
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+"/user/doAuth?intent=shop");
			return false;
		} else {
			logger.info("用户已登录,操作合法继续业务逻辑");
			return true;
		}

	}

	
}

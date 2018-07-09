package com.slf.wx.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:定义管理类相关操作的拦截类 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年9月13日
 * 
 */
public class AdminInterceptors implements HandlerInterceptor {

	private static final Logger logger = LogManager.getLogger(AdminInterceptors.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("管理类操作拦截:");
		if (request.getSession().getAttribute("currentStaff") == null) {
			logger.info("尚未登录，转向登录操作页面");
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+"/manage/login");
			return false;
		} else {
			logger.info("用户已登录,操作合法继续业务逻辑");
			return true;
		}


	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

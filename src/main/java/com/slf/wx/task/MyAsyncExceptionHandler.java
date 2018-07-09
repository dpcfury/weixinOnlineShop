package com.slf.wx.task;

import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	private static final Logger logger = LogManager.getLogger(MyAsyncExceptionHandler.class);
	 @Override  
	    public void handleUncaughtException(Throwable throwable, Method method, Object... args) {  
	        logger.error("调用异步任务出错了, message : " + throwable.getMessage());  
	    }  
}

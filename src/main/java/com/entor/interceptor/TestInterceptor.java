package com.entor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器和过滤器区别
 * 拦截器								过滤器
 * 
 * 1:基于Java反射机制                                                   	基于函数回调     
 * 2:不依赖Servlet容器                                              	依赖Servlet容器  
 * 3:只能对action起作用                                                 	几乎对所有请求起作用
 * 4:可以访问action上下文,值栈里的对象                    可以访问action上下文,值栈里的对象
 * 5:在Action生命周期中,拦截器可以多次被调用        过滤器只能在容器初始化时被调用一次
 * @author Administrator
 *
 */
public class TestInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("TestInterceptor preHandle");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("TestInterceptor afterCompletion");
	}
}

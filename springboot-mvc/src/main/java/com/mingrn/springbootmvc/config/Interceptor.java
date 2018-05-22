package com.mingrn.springbootmvc.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器配置
 *
 * @author MinGRn <br > 2018/5/21 17:06
 */
public class Interceptor extends HandlerInterceptorAdapter {

	/**
	 * 重写 preHandle 在请求之前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long preHandleTime = System.currentTimeMillis();
		request.setAttribute("preHandleTime", preHandleTime);
		return true;
	}

	/**
	 * 重新 postHandle 在请求之后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		Long preHandleTime = (Long) request.getAttribute("preHandleTime");
		request.removeAttribute("preHandleTime");
		Long postHandleTime = System.currentTimeMillis();
		System.out.println("本次请求处理时间为：" + (postHandleTime - preHandleTime) + "ms");
		request.setAttribute("handling time", postHandleTime - preHandleTime);
	}
}

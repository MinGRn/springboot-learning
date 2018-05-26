package com.mingrn.springbootmvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理配置类
 *
 * @author MinGRn <br > 2018/5/26 14:19
 */
@Order(-1000)
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		/*if (ex instanceof RuntimeException) {
			//500
		} else if (ex instanceof NoHandlerFoundException) {
			//404
		} else if (ex instanceof ServletException) {
			//
		} else {
			String message;
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				message = String.format("<<== 接口 [%s] 出现异常, %s.%s ==> 异常摘要: %s",
						request.getRequestURI(),
						handlerMethod.getBean().getClass().getName(),
						handlerMethod.getMethod().getName(),
						ex.getMessage());
			} else {
				message = ex.getMessage();
			}
		}*/
		///doing something
		return new ModelAndView("/500");
	}
}

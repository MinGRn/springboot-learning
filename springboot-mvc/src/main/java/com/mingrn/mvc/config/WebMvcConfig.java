package com.mingrn.mvc.config;

import com.mingrn.mvc.filter.ServiceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * WebMVC配置类
 *
 * @author MinGRn <br > 2018/5/22 17:54
 */
@EnableAsync
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final String VIEW_SUFFIX = ".ftl";
	private static final String VIEW_CONTENT_TYPE = "text/html;charset=UTF-8";

	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

	/**
	 * 注册拦截器Bean
	 */
	@Bean
	public Interceptor interceptor() {
		return new Interceptor();
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}

	/**
	 * 视图配置
	 * <p>
	 * 因在 application.properties 配置文件中配置 spring.freemarker.template-loader-path
	 * 因此,此处不需要配置 resolver.setPrefix(); 且在 template-loader-path 基础上配置也不起作用
	 * </p>
	 */
	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();

		resolver.setSuffix(VIEW_SUFFIX);
		resolver.setContentType(VIEW_CONTENT_TYPE);
		return resolver;
	}


	/**
	 * 静态资源访问,可注册多个静态资源
	 * <p>
	 * addResourceHandler:静态资源拦截规则
	 * addResourceLocations: 静态资源访问路径
	 * </p>
	 * webjars: 详阅README.md
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/MATE-INF/resource/webjars/**");
	}

	/**
	 * 这个Filter 解决页面跨域访问问题
	 */
	/*@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ServiceFilter());
		registration.addUrlPatterns("/*");
		registration.setName("MainFilter");
		registration.setAsyncSupported(true);
		registration.setOrder(1);
		return registration;
	}*/

	/**
	 * 全局异常处理
	 */
	@Bean
	public GlobalExceptionResolver globalExceptionResolver() {
		return new GlobalExceptionResolver();
	}

	/*@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add((request, response, handler, ex) -> {
			ModelAndView modelAndView = new ModelAndView();
			if (ex instanceof RuntimeException) {
				//500
				modelAndView.setViewName("/500");
			} else if (ex instanceof NoHandlerFoundException) {
				//404
				modelAndView.setViewName("/404");
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
			}
			return modelAndView;
		});
	}*/


}

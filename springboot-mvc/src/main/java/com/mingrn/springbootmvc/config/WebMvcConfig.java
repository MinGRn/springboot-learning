package com.mingrn.springbootmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final String VIEW_SUFFIX = ".ftl";
	private static final String VIEW_CONTENT_TYPE = "text/html;charset=UTF-8";

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


}

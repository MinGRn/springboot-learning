package com.mingrn.springbootmybatis.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid监控台配置:
 * 访问URL: http://ip:port/projectname/druid/index.html
 * 如:http:127.0.0.1:8080/druid/index.html
 *
 * @author MinGRn <br > 2018/5/27 15:21
 */
@Configuration
public class DruidWebStatConfig {


	/**
	 * 注册一个 StatViewServlet
	 *
	 * @return ServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		//添加初始化参数: initParams
		//添加白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		//添加黑名单(黑白名单共同存在时deny优先于allow)
		servletRegistrationBean.addInitParameter("deny", "10.0.9.208");
		//设置登录账号密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "123456");
		//是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}


	/**
	 * 注册一个 WebStatFilter
	 *
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		//添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		//添加过滤名称
		filterRegistrationBean.addServletNames("druidWebStatFilter");
		//添加不需要忽略的格式信息
		filterRegistrationBean.addInitParameter("exclusions", "*.css,*.js,*.gif,*.jpg,*.bmp,*.png,*.ico,*.mp4,/druid/*");
		return filterRegistrationBean;
	}
}
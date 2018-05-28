package com.mingrn.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域请求配置类
 *
 * @author MinGRn <br > 2018/5/26 15:28
 */
@Configuration
public class CorsConfig {

	/**
	 * 什么是跨域 ?
	 * 脚本文件服务器地址和请求的服务器地址不一样！说白了就是 ip、网络协议、port都一样时
	 * 就为同一个域,否则就是跨域!
	 * eg:
	 * http://www.123.com/index.html 调用 http://www.123.com/server.php (非跨域)
	 * http://www.123.com/index.html 调用 http://www.456.com/server.php (主域名不同:123/456,跨域)
	 * http://abc.123.com/index.html 调用 http://def.123.com/server.php (子域名不同:abc/def,跨域)
	 * http://www.123.com:8080/index.html 调用 http://www.123.com:8081/server.php (端口不同:8080/8081,跨域)
	 * http://www.123.com/index.html 调用 https://www.123.com/server.php (协议不同:http/https,跨域)
	 * 请注意：localhost和127.0.0.1虽然都指向本机,但也属于跨域。
	 * </p>
	 * 为什么要跨域 ?
	 * 这是由于Netscape提出一个著名的安全策略——同源策略造成的,这是浏览器对JavaScript施加的安全限制。
	 * 是防止外网的脚本恶意攻击服务器的一种措施。
	 * 当我们在浏览器中打开百度和谷歌两个网站时,百度浏览器在执行一个脚本的时候会检查这个脚本属于哪个页
	 * 面的,即检查是否同源,只有和百度同源的脚本才会被执行,如果没有同源策略,那随便的向百度中注入一个js
	 * 脚本,弹个恶意广告,通过js窃取信息,这就很不安全了。
	 * </p>
	 * addAllowedOrigin(String origin)方法是追加访问源地址。如果不使用 "*"(即允许全部访问源),则可以配置多条访问源来做控制。
	 * eg:
	 * corsConfiguration.addAllowedOrigin("http://www.aimaonline.cn/");
	 * corsConfiguration.addAllowedOrigin("http://test.aimaonline.cn/");
	 * </p>
	 * 参考:
	 * https://blog.csdn.net/qq779446849/article/details/53102925
	 * https://blog.csdn.net/Colton_Null/article/details/75195230
	 */

	private CorsConfiguration corsConfiguration() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//设置访问源地址
		corsConfiguration.addAllowedOrigin("*");
		//设置访问源请求头
		corsConfiguration.addAllowedHeader("*");
		//设置访问源请求方法
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	/**
	 * import CorsFilter package:
	 * org.springframework.web.filter
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//对接口配置跨域设置
		source.registerCorsConfiguration("/**", corsConfiguration());
		return new CorsFilter(source);
	}
}

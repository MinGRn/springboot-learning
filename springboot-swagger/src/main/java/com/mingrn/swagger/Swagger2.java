package com.mingrn.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * REST API
 * 访问地址:http://ip:port/projectname/swagger-ui.html
 * eg:http://127.0.0.1:8080/swagger-ui.html
 *
 * @author MinGRn <br > 2018/5/28 17:05
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mingrn.swagger.web"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger2 APIs")
				.description("SpringBoot 使用 Swagger2 构建 RESTful APIs")
				.termsOfServiceUrl("https://swagger.io/")
				.version("0.0.1-SNAPSHOT")
				.build();
	}
}

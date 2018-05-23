## springboot-mvc
> springboot-mvc 是 spingboot 集成 MVC 练习配置项目，采用的是 `freemarker` 模板引擎(至于
为什么不使用 `thymeleaf` 作为模板引擎完全是个人习惯)。其中在配置过程中踩了不少坑，也翻阅了不少资
料。因此特记录下来遇到的问题，方便以后翻阅

#### WebMvnConfig 配置类
> 该类继承 `WebMvcConfigurationSupport` ,虽然继承 `WebMvcConfigurerAdapter ` 类也可以
配置MVC。但是 `WebMvcConfigurationSupport` 的配置更全， `WebMvcConfigurerAdapter` 有
的他都有!而且在该项目中采用的 SpringBoot 版本是 2.0+ ,`WebMvcConfigurerAdapter` 已经过时
了最好实现 `WebMvcConfigurer` 重写其中的接口!
> ```java
>  @EnableWebMvc
>  public class WebMvcConfig implements WebMvcConfigurer {
>
>  }
>
>  @Configuration
>  public class WebMvcConfig extends WebMvcConfigurationSupport {
>
>  }
>
>  @Configuration
>  public class WebMvcConfig extends DelegatingWebMvcConfiguration {
>
>  }
>```
> 上面代码中需要在类中实现关于WebMvcAutoConfiguration的配置,而不是在application.properties中。
> - 详见: [博客园](https://www.cnblogs.com/sufferingStriver/p/9026764.html?_blank)

### pom依赖(主要)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

##### 视图配置
```java
    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(VIEW_SUFFIX);
        resolver.setContentType(VIEW_CONTENT_TYPE);
        return resolver;
    }
```

> 不配置 `resolver.setPrefix(VIEW_PREFIX);` 的原因是这里使用的是 `Freemarker` 模板引擎，
> `freemarker` 本身配置了 `templateLoaderPath` 而在 `viewResolver` 中不需要配置 `prefix`，
> 且路径前缀必须配置在 `templateLoaderPath` 中, 即在 `application.properties` 配置文件中配置
> 的 `spring.freemarker.template-loader-path=classpath:/temp`
> 注意的是，SpringBoot 默认模板访问路径是
>
>   > - `/resource/templates` 模板资源
>   > - `/resource/static` 静态资源
>
> 所以若不改编模板引擎资源路径的话不配置 `spring.freemarker.template-loader-path` 也是可以的

#### 静态资源访问
> ```java
>   @Override
>	public void addResourceHandlers(ResourceHandlerRegistry registry) {
>		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
>		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/MATE-INF/resource/webjars/**");
>	}
>```
> 静态资源访问重写 `addResourceHandlers` 方法,注册 `registry` 即可实现静态资源访问。
>
>   > - `addResourceHandler` 静态资源拦截规则
>   > - `addResourceLocations` 静态资源访问规则
>
> 静态资源访问可以注册多个！
> webjars: 在Web开发中,前端页面中主要采用CSS,JQuery等等！一般都是直接将这些资源拷贝在Web目录下,这种人工的
> 拷贝方式可能会产生版本误差,拷贝版本错误,前端页面就无法正确显示。webjars 就为此而生,将这些 web 前端资源打包
> 成 java 的 jar 包,然后借助 Maven 依赖库管理保证这些 web 资源的唯一性!
> WebJars 就是将js, css 等资源文件放到 `classpath:/META-INF/resources/webjars/` 中，然后打包成jar
> 发布到 maven 仓库中,另外 springboot 前端资源都是放在 `META-INF/resource` 资源目录下,如我们在采用
> SwaggerRESTFul 时,直接启动项目访问 ip:port/swagger-ui.html 有时是访问不到的,因为这些资源打包后是放在
> `META-INF/resource` 资源目录下的,因此我们需要在静态资源访问中配置
>
> `registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");`
>
> 来保证访问到静态资源


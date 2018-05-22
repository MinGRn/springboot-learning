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
> - 详见: <a href="https://www.cnblogs.com/sufferingStriver/p/9026764.html" target="view_window">博客园</a>

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
## springboot-mybatis
> 本项目用于练习springboot集成mybatis练习项目

#### pom依赖(主要)
```xml
<!--MySQL-->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.11</version>
</dependency>

<!--JDBC驱动-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
	<version>2.0.2.RELEASE</version>
</dependency>

<!--druid-->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid</artifactId>
	<version>1.1.9</version>
</dependency>

<!--MyBatis-->
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
	<version>1.3.2</version>
</dependency>

<!--TkMapper-->
<dependency>
	<groupId>tk.mybatis</groupId>
	<artifactId>mapper</artifactId>
	<version>4.0.2</version>
</dependency>
```

#### 连接池配置
> `spring.datasource.driver-class-name=com.mysql.jdbc.Driver`
>
>`spring.datasource.type=com.alibaba.druid.pool.DruidDataSource`
>
>`spring.datasource.url=jdbc:mysql://127.0.0.1:3306/spring-boot-learning?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=true`
>
>`spring.datasource.username=root`
>
>`spring.datasource.password=admin`
>
> !注意：若配置多数据源 `spring.datasource.url` 要改为 `spring.datasource.jdbc-url`

#### MyBatis
> 主要注意的是 `@MapperScan(basePackages = "com.mingrn.springbootmybatis.repository", sqlSessionTemplateRef = "sqlSessionTemplate")`
`MapperScan` 引用包一定要引用至 `tk.mybatis.spring.annotation.MapperScan`

#### SQL监控台
> mysql 监控采用的是阿里 Druid，主要目的是用于web监控台监控SQL执行状态和性能。
  demo 中采用的是在一个java类配置方式，注意这种配置方式一定要在配置文件中加上 `spring.datasource.filters=stat,wall,log4j2` 否则即使能登录监控台也无法
  统计SQL
>
> 另一种配置方式,这种配置一定要在启动类上加 `@ServletComponentScan` 注解
>
> 过滤配置器配置类
```java
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * alibab druid的过滤配置器
 * !!!!注意:
 * 一定要在启动类上加上 @ServletComponentScan 注解
 * 同时需要注意的是:启动类最好放在所有包的在下面
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
		initParams = {
				@WebInitParam(
						name = "exclusions",
						value = "*.css,*.js,*.gif,*.jpg,*.bmp,*.png,*.ico,*.mp4,/druid/*"
				)
		}//忽略资源
)
public class DruidWebStatFilter /*extends WebStatFilter*/ {
}
```
> 监控台配置类
```java
import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 访问URL: http://ip:port/projectname/druid/index.html
 * 如:http:127.0.0.1:8080/druid/index.html
 * !!!!注意:
 * 一定要在启动类上加上 @ServletComponentScan 注解
 * 同时需要注意的是:启动类最好放在所有包的在下面
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", initParams = {
		@WebInitParam(name = "allow", value = "127.0.0.1,10.0.9.209"),//白名单
//		@WebInitParam(name = "deny", value = "127.0.0.1"),//黑名单(黑白名单共同存在是,deny优于allow)
		@WebInitParam(name = "loginUsername", value = "admin"),//监控台访问账号
		@WebInitParam(name = "loginPassword", value = "123456"),//监控台访问密码
		@WebInitParam(name = "resetEnable", value = "true")//禁用HTML页面上的Reset All功能
})
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 1L;
}
```

> 另外对数据源连接池做些补充配置
```
# 初始化大小，最小，最大
spring.datasource.initialSize=1
spring.datasource.minIdle=1
spring.datasource.maxActive=1
# 配置获取连接等待超时的时间
spring.datasource.maxWait=60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.timeBetweenEvictionRunsMillis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=SELECT 1
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false
# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.poolPreparedStatements=true
spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 合并多个DruidDataSource的监控数据
spring.datasource.useGlobalDataSourceStat=true
```

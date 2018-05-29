# springboot-log4j2
> [Log4j1,Logback以及Log4j2性能测试对比](https://my.oschina.net/OutOfMemory/blog/789267)
> [官方](http://logging.apache.org/log4j/2.x/manual/async.html#AllAsync)

#### pom依赖
> springboot 集成 log4j2 首先要移除默认logback日志框架依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <!--排除logBak日志框架-->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>
<!--log4j2-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

#### properties 配置
> `logging.config=classpath:log4j2.xml`

#### 日志级别说明
> 日志记录器（Logger）的行为是分等级的。如下所示
>
> 分为 `OFF`、`FATAL`、`ERROR`、`WARN`、`INFO`、`DEBUG`、`ALL` 或者您定义的级别。Log4j建议只使用四个级别，
  优先级从高到低分别是 ERROR、WARN、INFO、DEBUG。通过在这里定义的级别，您可以控制到应用程序中
  相应级别的日志信息的开关。比如在这里定义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被
  打印出来，也是说大于等于的级别的日志才输出。

#### 日志输出格式
>
> - %d{yyyy-MM-dd HH:mm:ss, SSS} : 日志生产时间,输出到毫秒的时间
> - %-5level : 输出日志级别,-5表示左对齐并且固定输出5个字符,如果不足在右边补0
> - %c : logger的名称(%logger)
> - %t : 输出当前线程名称
> - %p : 日志输出格式
> - %m : 日志内容,即 logger.info("message")
> - %n : 换行符
> - %C : Java类名(%F)
> - %L : 行号
> - %M : 方法名
> - %l : 输出语句所在的行数, 包括类名、方法名、文件名、行数
> - hostName : 本地机器名
> - hostAddress : 本地ip地址
>
> eg: `<PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>`
> Console: `2018-05-29 17:21:19.466 [http-nio-8080-exec-1] INFO  com.mingrn.log4j2.web.Index - -----index--------`

#### 配置说明（在log4j2.xml 配置文件中已描述）
> `monitorInterval`: Log4j能够自动检测修改配置文件和重新配置本身, 设置间隔秒数.此处表示每隔600秒重读一次配置文件;
>
> `property`: 配置文件全局属性的声明，使用方式为：${声明的属性名称}。${sys:catalina.home}为tomcat部署路径，例如：/data/tomcat。
>
> `RollingRandomAccessFile`: 基本属性
> - `name`：Appender名称
> - `immediateFlush`：log4j2接收到日志事件时，是否立即将日志刷到磁盘。默认为true。
> - `fileName`：日志存储路径
> - `filePattern`：历史日志封存路径。其中%d{yyyyMMddHH}表示了封存历史日志的时间单位（目前单位为小时，yyyy表示年，MM表示月，dd表示天，HH表示小时，mm表示分钟，ss表示秒，SS表示毫秒）。注意后缀，log4j2自动识别zip等后缀，表示历史日志需要压缩。
>
> `TimeBasedTriggeringPolicy`:
> - `interval`：表示历史日志封存间隔时间，单位为filePattern设置的单位值
> - `modulate`：表示是否历史日志生成时间纠偏，纠偏以零点为基准进行。比如：15:16生成了msg.2017041715.zip文件，那么纠偏后会在16:00生成msg.2017041716.zip
>
> `ThresholdFilter`:
> - `level`，表示最低接受的日志级别，博客配置的为INFO，即我们期望打印INFO级别以上的日志。
> - `onMatch`，表示当日志事件的日志级别与level一致时，应怎么做。一般为ACCEPT，表示接受。
> - `onMismatch`，表示日志事件的日志级别与level不一致时，应怎么做。一般为DENY，表示拒绝。也可以为NEUTRAL表示中立。
> eg: 保存24小时历史日志，但不想用文件索引
```xml
<DefaultRolloverStrategy max="24">
    <Delete basePath="${MSG_LOG_HOME}" maxDepth="2">
        <IfFileName glob="*/msg.*.zip" />
        <IfLastModified age="24H" />
    </Delete>
</DefaultRolloverStrategy>
```
> 备注：
> 1. `age` 的单位：D、H、M、S，分别表示天、小时、分钟、秒
> 2. `basePath` 表示日志存储的基目录，maxDepth=“1”表示当前目录。因为我们封存的历史日志在basePath里面的backup目录，所以maxDepth设置为2
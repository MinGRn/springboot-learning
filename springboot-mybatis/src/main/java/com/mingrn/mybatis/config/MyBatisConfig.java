package com.mingrn.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mingrn.mybatis.constant.ProjectConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * MyBatis 配置类
 * MapperScan: tk.mybatis.spring.annotation.MapperScan
 *
 * @author MinGRn <br > 2018/5/27 13:37
 */
@Configuration
@MapperScan(basePackages = ProjectConstants.REPOSITORY_PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig {

	/**
	 * 数据源配置
	 */
	@Primary
	@Bean(name = "druidDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DruidDataSource druidDataSource() {
		return new DruidDataSource();
	}

	/**
	 * 工厂
	 */
	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 模板
	 */
	@Primary
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	/**
	 * 事物
	 */
	@Primary
	@Bean(name = "dataSourceTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}

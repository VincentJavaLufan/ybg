package com.ybg.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ybg.base.jdbc.DataBaseConstant;

@Configuration
public class DataBaseConfiguration {
	
	@Primary
	@Bean(DataBaseConstant.DB_SYS)
	@ConfigurationProperties("spring.datasource.druid.sys")
	public DataSource dataSourceSys() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(DataBaseConstant.DB_OA)
	@ConfigurationProperties("spring.datasource.druid.oa")
	public DataSource dataSourceOa() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(name = DataBaseConstant.DB_EDU)
	@ConfigurationProperties("spring.datasource.druid.edu")
	public DataSource dataSourceEdu() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = DataBaseConstant.JD_SYS)
	public JdbcTemplate sysJdbcTemplate(@Qualifier(DataBaseConstant.DB_SYS) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = DataBaseConstant.JD_OA)
	public JdbcTemplate oaJdbcTemplate(@Qualifier(DataBaseConstant.DB_OA) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = DataBaseConstant.JD_EDU)
	public JdbcTemplate eduJdbcTemplate(@Qualifier(DataBaseConstant.DB_EDU) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}

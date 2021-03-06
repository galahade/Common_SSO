package com.yang.young.common.sso.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public abstract class DataBaseConfig {

	@Bean
	public abstract DataSource dataSource();
	
	protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
		dataSource.setMaxActive(20);
		dataSource.setMaxIdle(8);
		dataSource.setMinIdle(8);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
	}
}

@Configuration
@Profile("localDBAuthentication")
class StandalongDatabaseConfig extends DataBaseConfig {

	

	@Bean
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		//dataSource.setUrl("jdbc:h2:tcp://localhost/~/sso");
		dataSource.setUrl("jdbc:h2:tcp://localhost:8043/~/sso");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setValidationQuery("SELECT 1");
		configureDataSource(dataSource);
		return dataSource;
	}
	
}

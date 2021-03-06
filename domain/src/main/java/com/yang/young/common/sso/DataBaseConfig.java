package com.yang.young.common.sso;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public abstract class DataBaseConfig {

	@Bean
	public abstract DataSource dataSource();

	protected void configureDataSource(
			org.apache.tomcat.jdbc.pool.DataSource dataSource) {
		dataSource.setMaxActive(20);
		dataSource.setMaxIdle(8);
		dataSource.setMinIdle(8);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
	}
}

@Configuration
class StandalongDatabaseConfig extends DataBaseConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:config/sql/schema.sql")
				.addScript("classpath:config/sql/Group_Role_data.sql").build();
		/*
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost:8043/~/sso");
		// dataSource.setUrl("jdbc:h2:mem:sso;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setValidationQuery("SELECT 1");
		configureDataSource(dataSource);
		*/
		return dataSource;
	}

}

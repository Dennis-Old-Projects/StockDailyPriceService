package com.multiplan.stockdaily.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatasourceConfig {

	@Bean
	public DataSource dataSource(Environment env) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//dataSource.setUrl("jdbc:mysql://db4free.net:3306/spring_cloud?useSSL=false");
		//dataSource.setUsername("spring_cloud");
		
		dataSource.setUrl(env.getRequiredProperty("db.url"));
		dataSource.setUsername(env.getRequiredProperty("db.userName"));
		dataSource.setPassword(env.getRequiredProperty("db.password"));
		
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	/*
	@Bean
	public EntityManager entityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stockdaily");
		EntityManager em = emf.createEntityManager();
		return em;
	}
	*/
	
	/*
	@Bean
	public DefaultManagedTaskExecutor getDefaultManagedTaskExecutor() {
		return new DefaultManagedTaskExecutor();
	}
	*/
}

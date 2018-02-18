/**
 * 
 */
package com.fabhotels.assignment.fabcards.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author prashant
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	private @Value("${database.driver_class}") String driverClass;
	private @Value("${database.connection_url}") String connectionUrl;
	private @Value("${database.username}") String username;
	private @Value("${database.password}") String password;
	private @Value("${database.dialect}") String dialect;
	
	@Autowired
	private WebappConfig webappConfig;
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDefaultAutoCommit(false);
		basicDataSource.setDriverClassName(driverClass);
		basicDataSource.setUrl(connectionUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setPackagesToScan("com.fabhotels.assignment.fabcards.model");
		localSessionFactoryBean.setDataSource(dataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.hbm2ddl.auto", webappConfig.getHbm2ddl());
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		return localSessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		hibernateTransactionManager.setFailEarlyOnGlobalRollbackOnly(true);
		return hibernateTransactionManager;
	}
}

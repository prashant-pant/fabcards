/**
 * 
 */
package com.fabhotels.assignment.fabcards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author prashant
 *
 */
@Configuration
@PropertySources({
	@PropertySource("classpath:properties/database.properties"),
	@PropertySource("classpath:properties/webapp.properties")
})
@Import({DatabaseConfig.class,WebSecurityConfig.class})
@ComponentScan(basePackages = { "com.fabhotels.assignment.fabcards.service","com.fabhotels.assignment.fabcards.dao"
})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class RootContext {

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public WebappConfig webappConfig(){
		return new WebappConfig();
	}
	
	@Bean
	public DatabaseConfig databaseConfig(){
		return new DatabaseConfig();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

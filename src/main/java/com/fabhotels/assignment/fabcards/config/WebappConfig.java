/**
 * 
 */
package com.fabhotels.assignment.fabcards.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author IISU44
 *
 */
@Component
public class WebappConfig {

	private @Value("${fabcards.hibernate.hbm2ddl.auto}") String hbm2ddl;

	public String getHbm2ddl() {
		return hbm2ddl;
	}

	public void setHbm2ddl(String hbm2ddl) {
		this.hbm2ddl = hbm2ddl;
	}

}

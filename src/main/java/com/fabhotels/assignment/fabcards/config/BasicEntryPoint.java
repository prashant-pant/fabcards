/**
 * 
 */
package com.fabhotels.assignment.fabcards.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * @author prashant
 *
 */
public class BasicEntryPoint extends BasicAuthenticationEntryPoint {
	
	
    @Override
    public void commence
      (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("FabHotels");
        super.afterPropertiesSet();
    }
}

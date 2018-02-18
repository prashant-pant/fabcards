/**
 * 
 */
package com.fabhotels.assignment.fabcards.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fabhotels.assignment.fabcards.dto.FabcardServiceExceptionDto;

/**
 * @author prashant
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver{

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		LOGGER.error("[ERROR]",ex);
		if(ex instanceof FabcardServiceExceptionDto){
			return resolveFabcardException(request, response, handler, ex);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		modelAndView.addObject("errorMessage", "Internal Server Error ! That's all I can tell you !");
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}
	
	public ModelAndView resolveFabcardException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		FabcardServiceExceptionDto fex = (FabcardServiceExceptionDto) ex;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setStatus(HttpStatus.BAD_REQUEST);
		modelAndView.addObject("errorCode", fex.getErrorCode());
		modelAndView.addObject("errorMessage", fex.getMessage());
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}

}

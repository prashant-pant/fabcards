/**
 * 
 */
package com.fabhotels.assignment.fabcards.service;

/**
 * @author prashant
 *
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}
	
	public ServiceException(String message){
		super(message);
	}

	public ServiceException(Throwable throwable){
		super(throwable);
	}
	
	public ServiceException(String message,Throwable throwable){
		super(message,throwable);
	}
	
}


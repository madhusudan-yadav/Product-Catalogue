package com.sapient.publicis.casestudy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8680074177570637752L;

	public ProductNotFoundException(String string) {
		super(string);
	}
}

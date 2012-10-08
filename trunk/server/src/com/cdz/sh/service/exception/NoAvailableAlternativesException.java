package com.cdz.sh.service.exception;

import com.cdz.sh.exception.SHException;

public class NoAvailableAlternativesException extends SHException {


	private static final long serialVersionUID = 1L;
	
	
	public NoAvailableAlternativesException(String errorCode, String message) {
		super(errorCode, message);
	}
	
}

package com.cdz.sh.dao.exception;

import com.cdz.sh.exception.SHException;

public class InvalidParameterException extends SHException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidParameterException(String errorCode, String message) {
		super(errorCode, message);
	}

	
}

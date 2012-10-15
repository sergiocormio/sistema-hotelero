package com.cdz.sh.service.exception;

import com.cdz.sh.exception.SHException;

public class InvalidOperationException extends SHException {

		
	private static final long serialVersionUID = 1L;

	public InvalidOperationException(String errorCode, String message){
		super(errorCode, message);
	}
}

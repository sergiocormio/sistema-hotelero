package com.cdz.sh.service.exception;

import com.cdz.sh.exception.SHException;

public class NoRateException extends SHException {

		
	private static final long serialVersionUID = 1L;

	public NoRateException(String errorCode, String message){
		super(errorCode, message);
	}
}

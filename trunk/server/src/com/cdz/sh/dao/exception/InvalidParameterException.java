package com.cdz.sh.dao.exception;

public class InvalidParameterException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	private long errorCode;

	public InvalidParameterException(long erroCode, String message){
		super(message);
		this.errorCode = erroCode;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	
}

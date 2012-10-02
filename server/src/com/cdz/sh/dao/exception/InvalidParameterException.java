package com.cdz.sh.dao.exception;

public class InvalidParameterException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public InvalidParameterException(String erroCode, String message){
		super(message);
		this.errorCode = erroCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
}

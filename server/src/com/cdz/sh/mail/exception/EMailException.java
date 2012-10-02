package com.cdz.sh.mail.exception;

public class EMailException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public EMailException(String errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}

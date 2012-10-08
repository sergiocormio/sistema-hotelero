package com.cdz.sh.exception;

public class SHException extends Exception {

	
private static final long serialVersionUID = 1L;
	
	private String errorCode; 
	
	public SHException(String errorCode, String message){
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

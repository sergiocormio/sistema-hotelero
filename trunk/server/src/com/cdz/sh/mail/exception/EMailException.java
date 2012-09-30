package com.cdz.sh.mail.exception;

public class EMailException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private long errorCode;

	public EMailException(long errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}

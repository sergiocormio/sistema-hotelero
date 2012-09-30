package com.cdz.sh.constants;

public class ExceptionErrorCodes {
	
	
	public static final long INVALID_OPERATION = 1l;
	
	/**
	 * CRUD errors: 1xx
	 */
	public static final long DUPLICATE_PK_VIOLATION_WHEN_CREATE = 100l;
	public static final long DUPLICATE_PK_VIOLATION_WHEN_UPDATE = 101l;

	public static final long ENTITY_TO_UPDATE_NOT_FOUND = 102l;
	public static final long ENTITY_TO_DELETE_NOT_FOUND = 103l;

	/**
	 * CORE and other error codes: 9xx 
	 */
	public static final long NO_PARAMETERS_SPECIFIED = 900l;

	public static final long INVALID_RESERVATION_FORM_STATE = 901l;

	public static final long OVERLAPING_DATE_RANGE = 902l;

	
	/**
	 * email error codes: 2xx
	 */
	public static final long EMAIL_NOT_SENT = 200l;
	public static final long INVALID_FROM_EMAIL = 201l;
	public static final long INVALID_FROM_PASSWORD = 202l;
	public static final long TO_LIST_EMPTY = 203l;
	public static final long INVALID_TO_EMAIL = 204l;
	public static final long INVALID_BODY = 205l;

	
	/**
	 * PDF report error codes: 3xx
	 */
	
	
	
	/**
	 * migration error codes: 4xx
	 */
	

	
	
}

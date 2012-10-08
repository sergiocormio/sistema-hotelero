package com.cdz.sh.constants;

public class ExceptionErrorCodes {
	
	
	public static final String INVALID_OPERATION = "invalidOperation";
	
	/**
	 * CRUD errors
	 */
	public static final String DUPLICATE_PK_VIOLATION_WHEN_CREATE = "PkViolationWhenCreate";
	public static final String DUPLICATE_PK_VIOLATION_WHEN_UPDATE = "PkViolationWhenCreate";

	public static final String ENTITY_TO_UPDATE_NOT_FOUND = "entityToUpdateNotFound";
	public static final String ENTITY_TO_DELETE_NOT_FOUND = "entityToDeleteNotFound";

	/**
	 * CORE and other error codes 
	 */
	public static final String NO_PARAMETERS_SPECIFIED = "noParamsSpeficied";
	public static final String INVALID_RESERVATION_FORM_STATE = "InvalidReservationForm";
	public static final String OVERLAPING_DATE_RANGE = "overlapingDateRange";

	public static final String NO_AVAILABLE_OCCUPATIONS = "noAvailableOccupations";
	public static final String NO_AVAILABLE_ALTERNATIVES = "noAvailableAlternatives";

	public static final String NO_RATE = "noRate";
	
	
	
	/**
	 * email error codes
	 */
	public static final String EMAIL_NOT_SENT = "emailNotSent";
	public static final String INVALID_FROM_EMAIL = "invalidFromEmail";
	public static final String INVALID_FROM_PASSWORD = "invalidFromPassword";
	public static final String TO_LIST_EMPTY = "toListEmpty";
	public static final String INVALID_TO_EMAIL = "invalidToEmail";
	public static final String INVALID_BODY = "invalidBody";




	
	/**
	 * PDF report error codes
	 */
	
	
	
	/**
	 * migration error codes
	 */
	

	
	
}

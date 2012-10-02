package com.cdz.sh.dao.exception;

import com.cdz.sh.constants.ExceptionErrorCodes;

/**
 * Generic Dao exception to be thrown on every dao methods. The idea is to encapsulate specific JPA exceptions.
 * (PersistenceException)
 * 
 * java.lang.Object
    java.lang.Throwable
        java.lang.Exception
            java.lang.RuntimeException
                javax.persistence.PersistenceException 
                    javax.persistence.EntityExistsException
                    javax.persistence.EntityNotFoundException
                    javax.persistence.LockTimeoutException
                    javax.persistence.NonUniqueResultException
                    javax.persistence.NoResultException
                    javax.persistence.OptimisticLockException
                    javax.persistence.PessimisticLockException
                    javax.persistence.QueryTimeoutException
                    javax.persistence.RollbackException
                    javax.persistence.TransactionRequiredException
                    
 * @author fede
 *
 */
public class DaoException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String errorCode;

	/*
	 * default constructor
	 */
	public DaoException(String detail){
		super(detail);
		this.errorCode = ExceptionErrorCodes.INVALID_OPERATION;
	}
	
	
	public DaoException(String errorCode, String detail){
		super(detail);
		this.errorCode = errorCode;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	
	
}

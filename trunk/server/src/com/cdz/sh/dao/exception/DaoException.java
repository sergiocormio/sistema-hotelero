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
	private long errorCode;

	/*
	 * default constructor
	 */
	public DaoException(String detail){
		super(detail);
		this.errorCode = ExceptionErrorCodes.INVALID_OPERATION;
	}
	
	
	public DaoException(long errorCode, String detail){
		super(detail);
		this.errorCode = errorCode;
	}


	public long getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}


	
	
}

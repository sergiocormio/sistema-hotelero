package com.cdz.sh.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.model.ExchangeRate;
import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class TestCrudDao {

	private ExchangeRateDao exchangeRateDao;
	
	@Before
	public void setup(){
		this.exchangeRateDao = new ExchangeRateDaoImpl();
	}
	
	
	@Test(expected=DaoException.class)
	public void testCreateExistingRecord() throws DaoException {
		
		ExchangeRate exchangeRate = null;
		try {
			exchangeRate = new ExchangeRate();
			exchangeRate.setId("ARS");
		
			this.exchangeRateDao.createRecord(exchangeRate);
		
			ExchangeRate exchangeRate2 = new ExchangeRate();
			exchangeRate2.setId("ARS");
			
			this.exchangeRateDao.createRecord(exchangeRate2);
		}
		catch (DaoException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.DUPLICATE_PK_VIOLATION_WHEN_CREATE));
			throw e;
		}
		finally{
			this.exchangeRateDao.deleteRecord(exchangeRate);
		}
	}
	
	@Test(expected=DaoException.class)
	public void testUpdateNonExistingRecord() throws DaoException {
		try{
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setId("ARS");
			
			this.exchangeRateDao.updateRecord(exchangeRate);
		}
		catch (DaoException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.ENTITY_TO_UPDATE_NOT_FOUND));
			throw e;
		}
	}
	
	
	@Test(expected=DaoException.class)
	public void testUpdateToExistingRecord() throws DaoException {
		ExchangeRate exchangeRate = null;
		ExchangeRate exchangeRate2 = null;
		try {
			exchangeRate = new ExchangeRate();
			exchangeRate.setId("ARS");
		
			this.exchangeRateDao.createRecord(exchangeRate);
			
			exchangeRate2 = new ExchangeRate();
			exchangeRate2.setId("UYU");
		
			this.exchangeRateDao.createRecord(exchangeRate2);
		
			exchangeRate2.setId("ARS");
			
			this.exchangeRateDao.updateRecord(exchangeRate2);
		}
		catch (DaoException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.DUPLICATE_PK_VIOLATION_WHEN_UPDATE));
			throw e;
		}
		finally{
			this.exchangeRateDao.deleteRecord(exchangeRate);
			this.exchangeRateDao.deleteRecord(exchangeRate2);
		}
	}
	
	@Test(expected=DaoException.class)
	public void testDeleteNonExistingRecord() throws DaoException {
		try {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setId("ARS");
		
			this.exchangeRateDao.deleteRecord(exchangeRate);
		
		}
		catch (DaoException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.ENTITY_TO_DELETE_NOT_FOUND));
			throw e;
		}
	}

}

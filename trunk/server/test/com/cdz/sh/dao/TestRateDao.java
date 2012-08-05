package com.cdz.sh.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RoomType;

public class TestRateDao {

	private RateDao rateDao;
	private RoomTypeDao roomTypeDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.rateDao = new RateDaoImpl();
		this.roomTypeDao = new RoomTypeDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testRetrieveRateDateBetweenLimits() throws DaoException {
		
		RoomType roomType = this.roomTypeDao.getRecordById(1L);
		
		Date date = new GregorianCalendar(2012, 7, 2).getTime();
		
		Rate rate = this.rateDao.retrieveRate(roomType, date);
		
		assertNotNull(rate);
	
	}
	
	@Test
	public void testRetrieveRateDateLowerLimit() throws DaoException {
		
		RoomType roomType = this.roomTypeDao.getRecordById(1L);
		
		Date date = new GregorianCalendar(2012, 7, 1).getTime();
		
		Rate rate = this.rateDao.retrieveRate(roomType, date);
		
		assertNotNull(rate);
	
	}
	
	@Test
	public void testRetrieveRateDateUpperLimit() throws DaoException {
		
		RoomType roomType = this.roomTypeDao.getRecordById(1L);
		
		Date date = new GregorianCalendar(2012, 7, 31).getTime();
		
		Rate rate = this.rateDao.retrieveRate(roomType, date);
		
		assertNotNull(rate);
	
	}

}

package com.cdz.sh.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.dao.impl.SeasonDaoImpl;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RatePK;
import com.cdz.sh.model.RoomType;
import com.cdz.sh.model.Season;

public class TestRateDao {

	private RateDao rateDao;
	private RoomTypeDao roomTypeDao;
	private SeasonDao seasonDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.rateDao = new RateDaoImpl();
		this.roomTypeDao = new RoomTypeDaoImpl();
		this.seasonDao = new SeasonDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test(expected=DaoException.class)
	public void testCreateExistingSeason() throws DaoException {
		
		RoomType roomType = this.roomTypeDao.getRecordById(1L);
		Season season = this.seasonDao.getRecordById(1L);
		
		RatePK ratePK = new RatePK();
		ratePK.setRoomType(roomType);
		ratePK.setSeason(season);
				
		Rate rate = new Rate();
		rate.setId(ratePK);
		
		this.rateDao.createRecord(rate);
	}
	
	
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

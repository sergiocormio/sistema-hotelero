package com.cdz.sh.dao;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.SeasonDaoImpl;
import com.cdz.sh.model.Season;

public class TestSeasonDao {

	private SeasonDao seasonDao;
	
	@Before
	public void setUp() throws Exception {
		this.seasonDao = new SeasonDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}


	/**
	 * CREATE
	 */
	
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:                    XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testCreateSeasonSuccessfully() throws DaoException {
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2044, 7, 1).getTime());
		season1.setDateTo(new GregorianCalendar(2044, 7, 3).getTime());
		season1.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season1);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2044, 7, 5).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 7).getTime());
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		this.seasonDao.deleteRecord(createdSeason);
		this.seasonDao.deleteRecord(createdSeason2);
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testCreateSeasonOverLapDateFrom() throws DaoException {
	
		Season createdSeason1 = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 1).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 3).getTime());
			createdSeason1 = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
			this.seasonDao.createRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason1);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	                XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testCreateSeasonOverLapDateTo() throws DaoException {
		
		Season createdSeason1 = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 5).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			createdSeason1 = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
			this.seasonDao.createRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason1);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	            XXXXXXXXX 
	 * Season to create:            XXXXXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testCreateSeason_SeasonCreatedIncludedInSeasonToCreate() throws DaoException {
	
		Season createdSeason1 = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
			createdSeason1 = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			this.seasonDao.createRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason1);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	        XXXXXXXXXXXXXXXXX 
	 * Season to create:                XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testCreateSeason_SeasonToCreateIncludedInSeasonCreated() throws DaoException {
	
		Season createdSeason1 = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			createdSeason1 = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
			this.seasonDao.createRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason1);
			throw new DaoException("mock");
		}
	}
	
	
	/**
	 * UPDATE
	 */
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:                    XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateSeasonSuccessfully() throws DaoException {
	
		Season season = new Season();
		season.setDateFrom(new GregorianCalendar(2044, 7, 1).getTime());
		season.setDateTo(new GregorianCalendar(2044, 7, 3).getTime());
		season.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setId(createdSeason.getId());
		season2.setDateFrom(new GregorianCalendar(2044, 7, 5).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 7).getTime());
		
		this.seasonDao.updateRecord(season2);
		
		this.seasonDao.deleteRecord(createdSeason);
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeasonOverLapDateFrom() throws DaoException {
	
		Season createdSeason = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 1).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 3).getTime());
			createdSeason = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setId(createdSeason.getId());
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
			
			this.seasonDao.updateRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	                XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeasonOverLapDateTo() throws DaoException {
	
		Season createdSeason = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 5).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			createdSeason = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setId(createdSeason.getId());
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
			
			this.seasonDao.updateRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	            XXXXXXXXX 
	 * Season to create:            XXXXXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeason_SeasonCreatedIncludedInSeasonToCreate() throws DaoException {
	
		Season createdSeason = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
			createdSeason = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setId(createdSeason.getId());
			season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			
			this.seasonDao.updateRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason);
			throw new DaoException("mock");
		}
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	        XXXXXXXXXXXXXXXXX 
	 * Season to create:                XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeason_SeasonToCreateIncludedInSeasonCreated() throws DaoException {
	
		Season createdSeason = null;
		
		try {
			Season season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
			season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
			createdSeason = this.seasonDao.createRecord(season1);
			
			Season season2 = new Season();
			season2.setId(createdSeason.getId());
			season2.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
			season2.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
			
			this.seasonDao.updateRecord(season2);
		}
		catch(DaoException e){
			this.seasonDao.deleteRecord(createdSeason);
			throw new DaoException("mock");
		}
	}

}

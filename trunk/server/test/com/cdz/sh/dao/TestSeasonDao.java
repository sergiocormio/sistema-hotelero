package com.cdz.sh.dao;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.SeasonDaoImpl;
import com.cdz.sh.model.Season;

public class TestSeasonDao {

	private SeasonDao seasonDao;
	
	@Before
	public void setUp() throws Exception {
		this.seasonDao = new SeasonDaoImpl();
		
		/**
		 * need to clear DB before each test from this file
		 */
		EntityManagerFactorySingleton.shutDown();
		EntityManagerFactorySingleton.getInstance();
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
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 1).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 3).getTime());
		Season createdSeason1 = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
		this.seasonDao.createRecord(season2);
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
		
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 5).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		Season createdSeason1 = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
		this.seasonDao.createRecord(season2);

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
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
		Season createdSeason1 = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		this.seasonDao.createRecord(season2);
		
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
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		Season createdSeason1 = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
		this.seasonDao.createRecord(season2);
		
	}
	
	
	/**
	 * UPDATE the same unique created season
	 */
	
	/**
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Existing Season: 	 	XXXXXXXXX 
	 * Same season to Update:                   XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateUniqueSeasonSuccessfully() throws DaoException {
	
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
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 		XXXXXXXXX 
	 * Same season to Update:           XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateUniqueSeasonOverLapTheSameDateFrom() throws DaoException {
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 1).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 3).getTime());
		Season createdSeason = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setId(createdSeason.getId());
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
		
		this.seasonDao.updateRecord(season2);
	
	}
	
	/**
 *	 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	 	                XXXXXXXXX 
	 * Same season to Update:           XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateUniqueSeasonOverLapTheSameDateTo() throws DaoException {
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 5).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		Season createdSeason = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setId(createdSeason.getId());
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 5).getTime());
		
		this.seasonDao.updateRecord(season2);

	}
	
	/**
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	     	        XXXXXXXXX 
	 * Same season to Update:           XXXXXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateUniqueSeason_TheSameSeasonCreatedIncludedInSeasonToUpdate() throws DaoException {
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
		Season createdSeason = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setId(createdSeason.getId());
		season2.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		
		this.seasonDao.updateRecord(season2);
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	        XXXXXXXXXXXXXXXXX 
	 * Season to create:                XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateSeason_SeasonToUpdateIncludedInTheSameSeasonCreated() throws DaoException {
	
		Season season1 = new Season();
		season1.setDateFrom(new GregorianCalendar(2013, 7, 3).getTime());
		season1.setDateTo(new GregorianCalendar(2013, 7, 7).getTime());
		Season createdSeason = this.seasonDao.createRecord(season1);
		
		Season season2 = new Season();
		season2.setId(createdSeason.getId());
		season2.setDateFrom(new GregorianCalendar(2013, 7, 4).getTime());
		season2.setDateTo(new GregorianCalendar(2013, 7, 6).getTime());
		
		this.seasonDao.updateRecord(season2);
		
	}
	
	
	
	/**
	 * UPDATE:
	 * 			Two seasons created
	 * 			update the second one...
	 */
	
	/**
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Season 1:		 	 	XXXXXXXXX
	 * Season 2 								XXXXXXXXX
	 * Season 2, update to:	                  					XXXXXX
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
		season2.setDateFrom(new GregorianCalendar(2044, 7, 5).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 7).getTime());
		
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		assertNotNull(createdSeason2);
		
		Season seasonToUpdate = new Season();
		seasonToUpdate.setId(createdSeason2.getId());
		seasonToUpdate.setDateFrom(new GregorianCalendar(2044, 7, 9).getTime());
		seasonToUpdate.setDateTo(new GregorianCalendar(2044, 7, 10).getTime());
		
		this.seasonDao.updateRecord(seasonToUpdate);
		
	}
	
	/**
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Season 1:			 	 	XXXXXXXXX
	 * Season 2 										XXXXXXXXX
	 * Season 2, update to:	       			XXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeasonOverLapAnotherDateFrom() throws DaoException {
	
		Season season = new Season();
		season.setDateFrom(new GregorianCalendar(2044, 7, 2).getTime());
		season.setDateTo(new GregorianCalendar(2044, 7, 4).getTime());
		season.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2044, 7, 7).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 9).getTime());
		
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		assertNotNull(createdSeason2);
		
		Season seasonToUpdate = new Season();
		seasonToUpdate.setId(createdSeason2.getId());
		seasonToUpdate.setDateFrom(new GregorianCalendar(2044, 7, 4).getTime());
		seasonToUpdate.setDateTo(new GregorianCalendar(2044, 7, 5).getTime());
		
		this.seasonDao.updateRecord(seasonToUpdate);
		
	}
	
	/**
     * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Season 1:		 	 		XXXXXXXXX
	 * Season 2 								XXXXXXXXXX
	 * Season 2, update to:	    XXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeasonOverLapAnotherDateTo() throws DaoException {
	
		Season season = new Season();
		season.setDateFrom(new GregorianCalendar(2044, 7, 2).getTime());
		season.setDateTo(new GregorianCalendar(2044, 7, 4).getTime());
		season.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2044, 7, 5).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 7).getTime());
		
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		assertNotNull(createdSeason2);
		
		Season seasonToUpdate = new Season();
		seasonToUpdate.setId(createdSeason2.getId());
		seasonToUpdate.setDateFrom(new GregorianCalendar(2044, 7, 1).getTime());
		seasonToUpdate.setDateTo(new GregorianCalendar(2044, 7, 2).getTime());
		
		this.seasonDao.updateRecord(seasonToUpdate);
	}
	
	/**
	 * 							1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Season 1:		 	 		XXXXX
	 * Season 2 									XXXXXXXXX
	 * Season 2, update to:	    XXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeason_AnotherSeasonCreatedIncludedInSeasonToUpdate() throws DaoException {
	
		Season season = new Season();
		season.setDateFrom(new GregorianCalendar(2044, 7, 2).getTime());
		season.setDateTo(new GregorianCalendar(2044, 7, 3).getTime());
		season.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2044, 7, 6).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 8).getTime());
		
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		assertNotNull(createdSeason2);
		
		Season seasonToUpdate = new Season();
		seasonToUpdate.setId(createdSeason2.getId());
		seasonToUpdate.setDateFrom(new GregorianCalendar(2044, 7, 1).getTime());
		seasonToUpdate.setDateTo(new GregorianCalendar(2044, 7, 4).getTime());
		
		this.seasonDao.updateRecord(seasonToUpdate);
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * Season 1:		 	XXXXXXXXXXXXXX
	 * Season 2 							XXXXXXXXXXXXXXXXX
	 * Season 2, update to:	    XXXXXX 
	 *  
	 * @throws DaoException
	 */
	@Test(expected=DaoException.class)
	public void testUpdateSeason_SeasonToUpdateIncludedInAnotherSeasonCreated() throws DaoException {
	
		Season season = new Season();
		season.setDateFrom(new GregorianCalendar(2044, 7, 1).getTime());
		season.setDateTo(new GregorianCalendar(2044, 7, 4).getTime());
		season.setName("Agosto 2044");
		Season createdSeason = this.seasonDao.createRecord(season);
		
		assertNotNull(createdSeason);
		
		Season season2 = new Season();
		season2.setDateFrom(new GregorianCalendar(2044, 7, 5).getTime());
		season2.setDateTo(new GregorianCalendar(2044, 7, 9).getTime());
		
		Season createdSeason2 = this.seasonDao.createRecord(season2);
		
		assertNotNull(createdSeason2);
		
		Season seasonToUpdate = new Season();
		seasonToUpdate.setId(createdSeason2.getId());
		seasonToUpdate.setDateFrom(new GregorianCalendar(2044, 7, 2).getTime());
		seasonToUpdate.setDateTo(new GregorianCalendar(2044, 7, 3).getTime());
		
		this.seasonDao.updateRecord(seasonToUpdate);
	}
	
	

}

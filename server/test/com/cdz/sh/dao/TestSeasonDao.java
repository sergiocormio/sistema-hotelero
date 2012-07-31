package com.cdz.sh.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.SeasonDaoImpl;

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
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testCreateSeasonOverLapDateFrom() throws DaoException {
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	                XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testCreateSeasonOverLapDateTo() throws DaoException {
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	            XXXXXXXXX 
	 * Season to create:            XXXXXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testCreateSeason_SeasonCreatedIncludedInSeasonToCreate() throws DaoException {
	
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
	public void testCreateSeason_SeasonToCreateIncludedInSeasonCreated() throws DaoException {
	
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
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateSeasonOverLapDateFrom() throws DaoException {
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	                XXXXXXXXX 
	 * Season to create:            XXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateSeasonOverLapDateTo() throws DaoException {
	
	}
	
	/**
	 * 						1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14
	 * 
	 * 	Existing Season: 	            XXXXXXXXX 
	 * Season to create:            XXXXXXXXXXXXXXXXX
	 *  
	 * @throws DaoException
	 */
	@Test
	public void testUpdateSeason_SeasonCreatedIncludedInSeasonToCreate() throws DaoException {
	
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
	public void testUpdateSeason_SeasonToCreateIncludedInSeasonCreated() throws DaoException {
	
	}

}

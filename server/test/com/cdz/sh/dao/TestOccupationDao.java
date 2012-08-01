package com.cdz.sh.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.model.Occupation;

public class TestOccupationDao {

	private OccupationDao occupationDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.occupationDao = new OccupationDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	@Test
	public void testRetrieveAllOccupations() throws DaoException {
		
		Collection<Occupation> occupations = this.occupationDao.retrieveAll();
		
		assertNotNull(occupations);
		
		/*
		 * reservation1: 1 - 3   =  3 days
		 * reservation2: 2 - 6   =  6 days
		 * reservation3: 10 - 19 = 10 days
		 * 				   TOTAL = 19 days
		 * 
		 */
		assertTrue(occupations.size() == 19);
		
	
	}
	
	
	@Test
	public void testRetrieveOccupationsShouldBeEmpty() throws DaoException {
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(new Date(), new Date());
		assertNotNull(occupations);
		assertTrue(occupations.isEmpty());
	
	}
	
	@Test
	public void testRetrieveOccupationLowerLimit() throws DaoException {
		
		Date dateFrom = new GregorianCalendar(2001, 7, 1).getTime();
		Date dateTo = new GregorianCalendar(2012, 7, 1).getTime();
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(dateFrom, dateTo);
		
		assertNotNull(occupations);
		
		// from roomType1 and roomType2
		assertTrue(occupations.size() == 2);
	
	}
	
	@Test
	public void testRetrieveOccupationUpperLimit() throws DaoException {
		
		Date dateFrom = new GregorianCalendar(2012, 7, 19).getTime();
		Date dateTo = new GregorianCalendar(2050, 11, 1).getTime();
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(dateFrom, dateTo);
		
		assertNotNull(occupations);
		
		// from roomType3
		assertTrue(occupations.size() == 1);
	
	}
	
	@Test
	public void testRetrieveOccupationLowerUpperLimit() throws DaoException {
		
		Date dateFrom = new GregorianCalendar(2012, 7, 18).getTime();
		Date dateTo = new GregorianCalendar(2012, 7, 19).getTime();
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(dateFrom, dateTo);
		
		assertNotNull(occupations);
		
		// from roomType3
		assertTrue(occupations.size() == 2);
	
	}
	

}

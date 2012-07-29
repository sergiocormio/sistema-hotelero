package com.cdz.sh.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
		this.occupationDao = new OccupationDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testRetrieveOccupationsShouldBeEmpty() throws DaoException {
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(new Date(), new Date());
		assertNotNull(occupations);
		assertTrue(occupations.isEmpty());
	
	}
	
	@Test
	public void testRetrieveOccupationLowerLimit() throws DaoException {
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(new Date(), new Date());
		assertNotNull(occupations);
		assertTrue(occupations.size() == 1);
	
	}
	
	@Test
	public void testRetrieveOccupationUpperLimit() throws DaoException {
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(new Date(), new Date());
		assertNotNull(occupations);
		assertTrue(occupations.size() == 1);
	
	}
	
	@Test
	public void testRetrieveOccupationLowerUpperLimit() throws DaoException {
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(new Date(), new Date());
		assertNotNull(occupations);
		assertTrue(occupations.size() == 2);
	
	}
	

}

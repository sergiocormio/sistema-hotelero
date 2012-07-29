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
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.model.Occupation;

public class TestRateDao {

	private RateDao rateDao;
	
	@Before
	public void setUp() throws Exception {
		this.rateDao = new RateDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testRetrieveRateDateBetweenLimits() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveRateDateLowerLimit() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveRateDateUpperLimit() throws DaoException {
		
		
	
	}

}

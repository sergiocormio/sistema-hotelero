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
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.ServiceType;

public class TestServiceTypeDao {

	private ServiceTypeDao serviceTypeDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.serviceTypeDao = new ServiceTypeDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testRetrieveAdditionalServiceTypes() throws DaoException {
		
		List<ServiceType> additionalServices = this.serviceTypeDao.retrieveAdditionalServices();
		
		assertNotNull(additionalServices);
		assertTrue(additionalServices.size() == 3);
	
	}
	
	@Test
	public void testRetrieveNonAdditionalServiceTypes() throws DaoException {
		
		
	
	}
	
	
}

package com.cdz.sh.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Region;

public class TestRegionDao {

	private RegionDao regionDao;
	private CountryDao countryDao;
		
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.regionDao = new RegionDaoImpl();
		this.countryDao = new CountryDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testCreateExistingSeason() throws DaoException {
		
		Country country = this.countryDao.getRecordById(1L);
	
		assertNotNull(country);
		
		List<Region> regions = this.regionDao.retrieveRegions(country);
		
		assertNotNull(regions);
		assertTrue(regions.size() == 2);
	}
		
		
}

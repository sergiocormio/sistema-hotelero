package com.cdz.sh.dao;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;

public class TestMasterDataFactory {

	
	@Before
	public void setup(){
		
	}
	
	
	@Test
	public void testCreateMasterData() throws DaoException {
		
		MasterDataFactory masterDataFactory = new MasterDataFactory();
		masterDataFactory.createMasterData();
	}
}
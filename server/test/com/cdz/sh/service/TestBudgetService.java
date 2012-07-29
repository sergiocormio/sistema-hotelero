package com.cdz.sh.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.service.impl.BudgetServiceImpl;
import com.cdz.sh.service.impl.ReservationFormServiceImpl;

public class TestBudgetService {

	private BudgetService budgetService;
	
	@Before
	public void setUp() throws Exception {
		this.budgetService = new BudgetServiceImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testGetBudgetCeroRoomTypeChange() throws DaoException {
		
		
	
	}
	
	@Test
	public void testGetBudgetOneRoomTypeChange() throws DaoException {
		
		
	
	}
	
	@Test
	public void testGetBudgetTwoRoomTypeChanges() throws DaoException {
		
		
	
	}
	
}

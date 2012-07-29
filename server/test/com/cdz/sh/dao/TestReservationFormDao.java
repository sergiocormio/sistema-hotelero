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
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.model.Occupation;

public class TestReservationFormDao {

	private ReservationFormDao reservationFormDao;
	
	@Before
	public void setUp() throws Exception {
		this.reservationFormDao = new ReservationFormDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testRetrieveReservationFormsWithoutFields() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateFrom() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateTo() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByCustomer() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByState() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateFromDateTo() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateFromDateToCustomer() throws DaoException {
		
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByAllFilters() throws DaoException {
		
		
	
	}
	
	
	
}

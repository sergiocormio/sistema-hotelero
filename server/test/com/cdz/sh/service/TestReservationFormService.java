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
import com.cdz.sh.service.impl.ReservationFormServiceImpl;

public class TestReservationFormService {

	private ReservationFormService reservationFormService;
	
	@Before
	public void setUp() throws Exception {
		this.reservationFormService = new ReservationFormServiceImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testBookSuccessfully() throws DaoException {
		
		
	
	}
		
	@Test
	public void testBookAlternativeWithoutOccupations() throws DaoException {
		
		
	
	}
	
}

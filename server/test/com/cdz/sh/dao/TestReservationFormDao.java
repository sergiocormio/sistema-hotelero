package com.cdz.sh.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;

public class TestReservationFormDao {

	private ReservationFormDao reservationFormDao;
	
	private CustomerDao customerDao;
	private DocumentTypeDao documentTypeDao;
	private RoomDao roomDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
				
		this.reservationFormDao = new ReservationFormDaoImpl();
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.customerDao = new CustomerDaoImpl();
		this.roomDao = new RoomDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test(expected=InvalidParameterException.class)
	public void testRetrieveReservationFormsWithoutFields() throws DaoException, InvalidParameterException {
		
		this.reservationFormDao.retrieveReservationForms(null, null, null, null);
		
	}
	
	@Test
	public void testRetrieveReservationFormByDateAndRoom() throws DaoException, InvalidParameterException {
		
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Room room = this.roomDao.getRecordById(1L);
		
		List<StateReservationForm> states = new ArrayList<StateReservationForm>();
		states.add(StateReservationForm.PRE_BOOKING);
		states.add(StateReservationForm.CONFIRMED);
		this.reservationFormDao.retrieveReservationForms(calendar.getTime(), room, states);
		
	}
	
	
	
	@Test
	public void testRetrieveReservationFormsByDateFrom() throws DaoException, InvalidParameterException {
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 19);
		Date dateFrom = calendar.getTime();
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, null, null, null);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 1);
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateTo() throws DaoException, InvalidParameterException {
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 3);
		Date dateTo = calendar.getTime();
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(null, dateTo, null, null);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 1);
		
	
	}
	
	@Test
	public void testRetrieveReservationFormsByCustomer() throws DaoException, InvalidParameterException {
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
				
		Customer customer = this.customerDao.getRecordById(1L);
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(null, null, customer, null);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 2);
	
	}
	
	@Test
	public void testRetrieveReservationFormsByState() throws DaoException, InvalidParameterException {
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(null, null, null, StateReservationForm.PRE_BOOKING);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 1);
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateFromDateTo() throws DaoException, InvalidParameterException {
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		GregorianCalendar calendar2 = new GregorianCalendar(2012, 7, 19);
		Date dateTo = calendar2.getTime();
		
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, dateTo, null, null);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 3);
	
	}
	
	@Test
	public void testRetrieveReservationFormsByDateFromDateToCustomer() throws DaoException, InvalidParameterException {
		
		GregorianCalendar calendar = new GregorianCalendar(2000, 7, 1);
		Date dateFrom = calendar.getTime();
		
		GregorianCalendar calendar2 = new GregorianCalendar(2012, 7, 19);
		Date dateTo = calendar2.getTime();
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		Customer customer = this.customerDao.getRecordById(1L);
		
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, dateTo, customer, null);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 2);
	
	}
	
	@Test
	public void testRetrieveReservationFormsByAllFilters() throws DaoException, InvalidParameterException {
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		GregorianCalendar calendar2 = new GregorianCalendar(2012, 7, 19);
		Date dateTo = calendar2.getTime();
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
						
		Customer customer = this.customerDao.getRecordById(1L);
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, dateTo, customer, StateReservationForm.PRE_BOOKING);
		
		assertNotNull(reservationForms);
		
		assertTrue(reservationForms.size() == 1);
	
	}
	
	
	
}

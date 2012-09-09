package com.cdz.sh.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.DocumentTypeDao;
import com.cdz.sh.dao.DummyScenarioBuilder;
import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.service.impl.ReservationFormServiceImpl;
import com.cdz.sh.util.DateUtil;

public class TestReservationFormService {

	private ReservationFormService reservationFormService;
	private RoomDao roomDao;
	private CustomerDao customerDao;
	private DocumentTypeDao documentTypeDao;
	private OccupationDao occupationDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
				
		this.roomDao = new RoomDaoImpl();
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.customerDao = new CustomerDaoImpl();
		this.occupationDao = new OccupationDaoImpl();
		this.reservationFormService = new ReservationFormServiceImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testBookSuccessfully() throws DaoException, InvalidParameterException {
		
		Alternative alternative = new Alternative(4);
		
		ReservationForm reservationForm = buildReservationForm();
		
		List<Occupation> occupations = createOccupationWithoutRoomChange(reservationForm);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		
		ReservationForm createdReservation = this.reservationFormService.book(alternative, reservationForm);
		
		assertNotNull(createdReservation);
		
		assertNotNull(createdReservation.getId());
		
		// delete first the occupations, then de reservationForm, to recpect the FK constraint
		
		for (Occupation occupation : occupations) {
			this.occupationDao.deleteRecord(occupation);
		}
		
		this.reservationFormService.deleteRecord(createdReservation);
	
	}
	
	
	@Test(expected=InvalidParameterException.class)
	public void testBookReservationFormState_NOT_PreReserva() throws DaoException, InvalidParameterException {
		
		Alternative alternative = new Alternative(4);
		
		ReservationForm reservationForm = new ReservationForm();
		reservationForm.setState(StateReservationForm.confirmada);
		
		ReservationForm createdReservation = this.reservationFormService.book(alternative, reservationForm);
		
	}
	
	private ReservationForm buildReservationForm() throws DaoException{
		
		ReservationForm reservationForm = new ReservationForm();
			
		GregorianCalendar calendar = new GregorianCalendar(2999, 7, 1);
		reservationForm.setDateFrom(calendar.getTime());
		
		calendar.add(GregorianCalendar.DATE, 2);
		
		reservationForm.setDateTo(calendar.getTime());

		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		CustomerPK customerPKFede = new CustomerPK();
		customerPKFede.setDocType(docTypeDNI);
		customerPKFede.setIdNumber("33103189");
		
		reservationForm.setCustomer(this.customerDao.getRecordById(customerPKFede));
		reservationForm.setState(StateReservationForm.pre_reserva);
		
		return reservationForm;
	}
	
	private ReservationForm buildUniqueReservationForm() throws DaoException{
		
		ReservationForm reservationForm = new ReservationForm();
			
		GregorianCalendar calendar = new GregorianCalendar(9999, 7, 1);
		reservationForm.setDateFrom(calendar.getTime());
		
		calendar.add(GregorianCalendar.DATE, 2);
		
		reservationForm.setDateTo(calendar.getTime());

		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		CustomerPK customerPKFede = new CustomerPK();
		customerPKFede.setDocType(docTypeDNI);
		customerPKFede.setIdNumber("33103189");
		
		reservationForm.setCustomer(this.customerDao.getRecordById(customerPKFede));
		reservationForm.setState(StateReservationForm.pre_reserva);
		
		return reservationForm;
	}
	
	
	private List<Occupation> createOccupationWithoutRoomChange(ReservationForm reservationForm) throws DaoException {
		
		List<Occupation> occupations = new ArrayList<Occupation>();
		
		Date date = reservationForm.getDateFrom();
		while(!date.after(reservationForm.getDateTo())){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(1L));
			occupationPK.setReservationForm(reservationForm);
						
			Occupation 	occupation = new Occupation();
			occupation.setId(occupationPK);
		
			occupations.add(occupation);
			
			date = DateUtil.getNextDay(date);
		}
		return occupations;
	}
	
		
	@Test
	public void testConfirmReservationWithoutOverlaping() throws DaoException, InvalidParameterException {
		
		Alternative alternative = new Alternative(4);
		
		ReservationForm reservationForm = buildUniqueReservationForm();
		
		List<Occupation> occupations = createOccupationWithoutRoomChange(reservationForm);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		reservationForm.setState(StateReservationForm.pre_reserva);
				
		ReservationForm createdReservation = this.reservationFormService.book(alternative, reservationForm);
		
		createdReservation.setState(StateReservationForm.confirmada);
		
		List<ReservationForm> reservationFormsCanceled = this.reservationFormService.retrieveReservationForms(null, null, null, StateReservationForm.cancelada);
		
		this.reservationFormService.updateRecord(createdReservation);
		
		List<ReservationForm> updatedReservationFormsCanceled = this.reservationFormService.retrieveReservationForms(null, null, null, StateReservationForm.cancelada);
				
		assertTrue(updatedReservationFormsCanceled.size() == reservationFormsCanceled.size());
	}
	
	
	@Test
	public void testConfirmReservationWithOverlaping() throws DaoException, InvalidParameterException {
		
		Alternative alternative1 = new Alternative(4);
		
		ReservationForm reservationForm1 = buildReservationForm();
				
		List<Occupation> occupations = createOccupationWithoutRoomChange(reservationForm1);
		
		for (Occupation occupation : occupations) {
			alternative1.addOccupation(occupation);
		}
		
		/***********/

		Alternative alternative2 = new Alternative(4);
		
		ReservationForm reservationForm2 = buildReservationForm();
				
		occupations = createOccupationWithoutRoomChange(reservationForm2);
		
		for (Occupation occupation : occupations) {
			alternative2.addOccupation(occupation);
		}
		
		/***********/
		
		Alternative alternative3 = new Alternative(4);
		
		ReservationForm reservationForm3 = buildReservationForm();
				
		occupations = createOccupationWithoutRoomChange(reservationForm3);
		
		for (Occupation occupation : occupations) {
			alternative3.addOccupation(occupation);
		}
		
		/***********/
		
		reservationForm3.setState(StateReservationForm.pre_reserva);
				
		ReservationForm createdReservation1 = this.reservationFormService.book(alternative1, reservationForm1);
		ReservationForm createdReservation2 = this.reservationFormService.book(alternative2, reservationForm2);
		ReservationForm createdReservation3 = this.reservationFormService.book(alternative3, reservationForm3);
		
		Collection<ReservationForm> retrieveAll = this.reservationFormService.retrieveAll();
		
		createdReservation1.setState(StateReservationForm.confirmada);
		
		List<ReservationForm> reservationFormsCanceled = this.reservationFormService.retrieveReservationForms(null, null, null, StateReservationForm.cancelada);
		
		this.reservationFormService.updateRecord(createdReservation1);
		
		List<ReservationForm> updatedReservationFormsCanceled = this.reservationFormService.retrieveReservationForms(null, null, null, StateReservationForm.cancelada);
				
		assertTrue(updatedReservationFormsCanceled.size() == reservationFormsCanceled.size()+2);
	}
	
	@Test
	public void testExportData() throws DaoException {
		
		ReservationForm reservationForm = this.reservationFormService.getRecordById(1L);
		
		this.reservationFormService.exportData(reservationForm, "C:\\pdf\\reservationForm.pdf");
	
	}
	
	
	
}

package com.cdz.sh.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
import com.cdz.sh.dao.StateReservationFormDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.dao.impl.StateReservationFormDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.service.impl.ReservationFormServiceImpl;

public class TestReservationFormService {

	private ReservationFormService reservationFormService;
	private RoomDao roomDao;
	private CustomerDao customerDao;
	private StateReservationFormDao stateReservationFormDao;
	private DocumentTypeDao documentTypeDao;
	private OccupationDao occupationDao;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
				
		this.roomDao = new RoomDaoImpl();
		this.stateReservationFormDao = new StateReservationFormDaoImpl();
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.customerDao = new CustomerDaoImpl();
		this.occupationDao = new OccupationDaoImpl();
		this.reservationFormService = new ReservationFormServiceImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testBookSuccessfully() throws DaoException {
		
		Alternative alternative = new Alternative();
		
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
		reservationForm.setState(this.stateReservationFormDao.getRecordById(1L));
		
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
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		return occupations;
	}
		
	@Test
	public void testExportData() throws DaoException {
		
		ReservationForm reservationForm = this.reservationFormService.getRecordById(1L);
		
		this.reservationFormService.exportData(reservationForm, "C:\\pdf\\reservationForm.pdf");
	
	}
	
	
	
}

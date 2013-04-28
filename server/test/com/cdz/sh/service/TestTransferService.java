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
import com.cdz.sh.model.Transfer;
import com.cdz.sh.service.exception.InvalidOperationException;
import com.cdz.sh.service.impl.ReservationFormServiceImpl;
import com.cdz.sh.service.impl.ServiceTypesBuilder;
import com.cdz.sh.service.impl.TransferServiceImpl;
import com.cdz.sh.util.DateUtil;

public class TestTransferService {

	private TransferService transferService;
	
	@Before
	public void setUp() throws Exception {
		
//		ServiceTypesBuilder serviceTypesBuilder = new ServiceTypesBuilder();
//		serviceTypesBuilder.buildServiceTypes();
		
		this.transferService = new TransferServiceImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testCreateRecordsSuccessfully() throws DaoException, InvalidOperationException {
		
		Transfer t1 = new Transfer();
		t1.setDate(new Date());
		
		Transfer t2 = new Transfer();
		t2.setDate(new Date());
		
		
		List<Transfer> returnedRecords = this.transferService.createRecords(t1, t2);
		
		assertNotNull(returnedRecords.get(0));
		assertNotNull(returnedRecords.get(1));
		
		assertNotNull(returnedRecords.get(0).getRelatedTransfer());
		assertNotNull(returnedRecords.get(1).getRelatedTransfer());
		
		assertTrue(returnedRecords.get(0).getRelatedTransfer().getId() == returnedRecords.get(1).getId());
		assertTrue(returnedRecords.get(1).getRelatedTransfer().getId() == returnedRecords.get(0).getId());
		
		List<Transfer> createdRecords = (List)this.transferService.retrieveAll();
		assertTrue(createdRecords.size() == 2);
		
		assertNotNull(createdRecords.get(0));
		assertNotNull(createdRecords.get(1));
		
		assertNotNull(createdRecords.get(0).getRelatedTransfer());
		assertNotNull(createdRecords.get(1).getRelatedTransfer());
		
		assertTrue(createdRecords.get(0).getRelatedTransfer().getId() == createdRecords.get(1).getId());
		assertTrue(createdRecords.get(1).getRelatedTransfer().getId() == createdRecords.get(0).getId());
		
	}
	
	
	@Test
	public void deleteRecords() throws DaoException, InvalidOperationException {
		
		Transfer t1 = new Transfer();
		t1.setDate(new Date());
		
		Transfer t2 = new Transfer();
		t2.setDate(new Date());
		
		List<Transfer> returnedRecords = this.transferService.createRecords(t1, t2);

				
		this.transferService.deleteRecord(returnedRecords.get(0));
		
		assertTrue(this.transferService.retrieveAll().size() == 0);
	}
	
}

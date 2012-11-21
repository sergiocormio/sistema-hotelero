package com.cdz.sh.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.DummyScenarioBuilder;
import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ServiceAddedInBasePrice;
import com.cdz.sh.model.ServiceTypeModality;
import com.cdz.sh.service.exception.NoRateException;
import com.cdz.sh.service.impl.BudgetServiceImpl;

public class TestBudgetService {

	private BudgetService budgetService;
	private RoomDao roomDao;
	
	@Before
	public void setUp() throws Exception {
		
		/**
		 * need to clear DB before each test from this file
		 */
		EntityManagerFactorySingleton.shutDown();
		EntityManagerFactorySingleton.getInstance();
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
				
		DummyScenarioBuilder dummyScenarioBuilder = new DummyScenarioBuilder();
		dummyScenarioBuilder.createDummyScenario();
		
		this.budgetService = new BudgetServiceImpl();
		this.roomDao = new RoomDaoImpl();
	}

	@After
	public void tearDown() throws Exception {}

	
	
	@Test
	public void testGetBudgetCeroRoomTypeChange() throws DaoException, NoRateException {
				
		Alternative alternative = new Alternative(4);
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 3).getTime();
		
		List<Occupation> occupations = createOccupationWithoutRoomChange(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
				
		Budget budget = this.budgetService.populateBudget(alternative).getBudget();
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice().doubleValue() == 450d);
		assertTrue(budget.getAdditionalServices().size() == 7);
		assertTrue(budget.getServicesToBeAddedInBasePrice().size() == 2);
		
		
		for(ServiceAddedInBasePrice serviceIncludedInBasePrice : budget.getServicePricesAddedInBasePrice()){
			Double price = serviceIncludedInBasePrice.getPrice();
			
			if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_PERSON)){
				
				assertTrue(price.doubleValue() == 510d);	
			}
			else if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_NIGHT)){
				
				assertTrue(price.doubleValue() == 480d);
			}
		}
		
		assertTrue(budget.getBasePricePlusAllServicesIncludedInBasePrice().doubleValue() == 540d);
	}
	
	@Test
	public void testGetBudgetOneRoomTypeChange() throws DaoException, NoRateException {
		
		Alternative alternative = new Alternative(4);
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 6).getTime();
		
		List<Occupation> occupations = createOccupationWithOneRoomChange(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		Budget budget = this.budgetService.populateBudget(alternative).getBudget();
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice().doubleValue() == 1170d);
		assertTrue(budget.getAdditionalServices().size() == 7);
		assertTrue(budget.getServicesToBeAddedInBasePrice().size() == 2);
		
		for(ServiceAddedInBasePrice serviceIncludedInBasePrice : budget.getServicePricesAddedInBasePrice()){
			Double price = serviceIncludedInBasePrice.getPrice();
			
			if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_PERSON)){
				
				assertTrue(price.doubleValue() == 1290d);	
			}
			else if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_NIGHT)){
				
				assertTrue(price.doubleValue() == 1230d);
			}
		}
		
		assertTrue(budget.getBasePricePlusAllServicesIncludedInBasePrice().doubleValue() == 1350d);
	
	}
	
	
	@Test
	public void testGetBudgetTwoRoomTypeChanges() throws DaoException, NoRateException {
		
		Alternative alternative = new Alternative(4);
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 10).getTime();
		
		List<Occupation> occupations = createOccupationWithTwoRoomChanges(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		Budget budget = this.budgetService.populateBudget(alternative).getBudget();
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice().doubleValue() == 2430d);
		assertTrue(budget.getAdditionalServices().size() == 7);
		assertTrue(budget.getServicesToBeAddedInBasePrice().size() == 2);
		
		
		for(ServiceAddedInBasePrice serviceIncludedInBasePrice : budget.getServicePricesAddedInBasePrice()){
			Double price = serviceIncludedInBasePrice.getPrice();
			
			if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_PERSON)){
				
				assertTrue(price.doubleValue() == 2630d);	
			}
			else if(serviceIncludedInBasePrice.getServiceType().getModality().equals(ServiceTypeModality.PER_NIGHT)){
				
				assertTrue(price.doubleValue() == 2530d);
			}
		}
		assertTrue(budget.getBasePricePlusAllServicesIncludedInBasePrice().doubleValue() == 2730d);
		
	}
	
	
	
	private List<Occupation> createOccupationWithTwoRoomChanges(Date dateFrom, Date dateTo) throws DaoException {
	
		List<Occupation> occupations = new ArrayList<Occupation>();
		
		Date date = dateFrom;
		// first 3 days on roomtype 3
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(3L));
			
			Occupation occupation = new Occupation();
			occupation.setId(occupationPK);
			occupations.add(occupation);
		
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		//second 3 days on roomtype 4
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(4L));
			
			Occupation occupation = new Occupation();
			occupation.setId(occupationPK);
			occupations.add(occupation);

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		// remaining 4 days on roomtype 5
		while(!date.after(dateTo)){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(5L));
			
			Occupation occupation = new Occupation();
			occupation.setId(occupationPK);
			occupations.add(occupation);	

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		return occupations;	
	}
	

	private List<Occupation> createOccupationWithOneRoomChange(Date dateFrom, Date dateTo) throws DaoException {
		
		List<Occupation> occupations = new ArrayList<Occupation>();
		
		Date date = dateFrom;
		// first 3 days on roomtype 2
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(2L));
			
			Occupation occupation = new Occupation();
			occupation.setId(occupationPK);
		
			occupations.add(occupation);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		// remaining 3 days on roomtype 3
		while(!date.after(dateTo)){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(3L));
			
			Occupation occupation = new Occupation();
			occupation.setId(occupationPK);
			occupations.add(occupation);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		return occupations;
	}

	
	private List<Occupation> createOccupationWithoutRoomChange(Date dateFrom, Date dateTo) throws DaoException {
		
		List<Occupation> occupations = new ArrayList<Occupation>();
		
		while(!dateFrom.after(dateTo)){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(dateFrom);
			occupationPK.setRoom(this.roomDao.getRecordById(1L));
						
			Occupation 	occupation = new Occupation();
			occupation.setId(occupationPK);
					
			occupations.add(occupation);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(dateFrom);
			calendar.add(GregorianCalendar.DATE, 1);
			dateFrom = calendar.getTime();
		}
		return occupations;
	}
}

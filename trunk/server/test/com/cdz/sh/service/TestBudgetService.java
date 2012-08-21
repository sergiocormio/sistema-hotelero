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
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.service.impl.BudgetServiceImpl;

public class TestBudgetService {

	private BudgetService budgetService;
	private RoomDao roomDao;
	
	@Before
	public void setUp() throws Exception {
		
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
	public void testGetBudgetCeroRoomTypeChange() throws DaoException {
				
		Alternative alternative = new Alternative();
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 3).getTime();
		
		List<Occupation> occupations = createOccupationWithoutRoomChange(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
				
		Budget budget = this.budgetService.getBudget(alternative);
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice() == 450);
		assertTrue(budget.getPriceWithBreakfast() == 480);
		assertTrue(budget.getPriceWithParking() == 495);
		assertTrue(budget.getPriceWithBreakfastAndParking() == 525);
				
	}
	
	@Test
	public void testGetBudgetOneRoomTypeChange() throws DaoException {
		
		Alternative alternative = new Alternative();
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 6).getTime();
		
		List<Occupation> occupations = createOccupationWithOneRoomChange(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		Budget budget = this.budgetService.getBudget(alternative);
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice() == 1170);
		assertTrue(budget.getPriceWithBreakfast() == 1230);
		assertTrue(budget.getPriceWithParking() == 1260);
		assertTrue(budget.getPriceWithBreakfastAndParking() == 1320);
		
	
	}
	
	
	@Test
	public void testGetBudgetTwoRoomTypeChanges() throws DaoException {
		
		Alternative alternative = new Alternative();
		
		Date dateFrom  = new GregorianCalendar(2012, 7, 1).getTime();
	
		Date dateTo = new GregorianCalendar(2012, 7, 10).getTime();
		
		List<Occupation> occupations = createOccupationWithTwoRoomChanges(dateFrom, dateTo);
		
		for (Occupation occupation : occupations) {
			alternative.addOccupation(occupation);
		}
		
		Budget budget = this.budgetService.getBudget(alternative);
		
		assertNotNull(budget);
		
		assertTrue(budget.getBasePrice() == 2430);
		assertTrue(budget.getPriceWithBreakfast() == 2530);
		assertTrue(budget.getPriceWithParking() == 2580);
		assertTrue(budget.getPriceWithBreakfastAndParking() == 2680);
		
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

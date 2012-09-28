package com.cdz.sh.service.core;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.service.OccupationService;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllBusy_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllEmpty_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_LessDaysThanExpected_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_NoRootAlternatives_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_Room3Busy_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_Rooms_2_3_busy_SameRoomType;
import com.cdz.sh.service.core.scenarios.roomchange.ScenarioBuilder_InvalidRoomChangesTwoAlternatives_SameRoomType;
import com.cdz.sh.service.core.scenarios.roomchange.ScenarioBuilder_InvalidRoomChanges_FirstDay_SameRoomType;
import com.cdz.sh.service.core.scenarios.roomchange.ScenarioBuilder_RoomChangesExceeded_SameRoomType;
import com.cdz.sh.service.core.scenarios.roomchange.ScenarioBuilder_ValidRoomChange_LastDay_SameRoomType;
import com.cdz.sh.service.core.scenarios.roomchange.ScenarioBuilder_ValidRoomChangesTwoAlternatives_SameRoomType;
import com.cdz.sh.service.exception.NoAvailableAlternativesException;
import com.cdz.sh.service.exception.NoRateException;
import com.cdz.sh.service.impl.OccupationServiceImpl;

public class TestCore_SameRoomType {

	private OccupationService occupationService;
	
	@Before
	public void setUp() throws Exception {
		this.occupationService = new OccupationServiceImpl();
		
		/**
		 * need to clear DB before each test from this file
		 */
		EntityManagerFactorySingleton.shutDown();
		EntityManagerFactorySingleton.getInstance();
	}

	
	@Test
	public void testAllEmpty() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_AllEmpty_SameRoomType scenarioBuilder = new ScenarioBuilder_AllEmpty_SameRoomType();
		scenarioBuilder.createDummyScenario();
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		assertTrue(alternatives.size() == 3);
		
	}
	
	
	@Test(expected=NoRateException.class)
	public void testAllEmptySelectingRangeOfDatesOutSideASeason() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_AllEmpty_SameRoomType scenarioBuilder = new ScenarioBuilder_AllEmpty_SameRoomType();
		scenarioBuilder.createDummyScenario();
		
		GregorianCalendar calendar = new GregorianCalendar(2020, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		assertTrue(alternatives.size() == 3);
		
	}
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testAllBusy() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_AllBusy_SameRoomType scenarioBuilder = new ScenarioBuilder_AllBusy_SameRoomType();
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
				
	}
	
	@Test
	public void testRoom3Busy() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_Room3Busy_SameRoomType scenarioBuilder = new ScenarioBuilder_Room3Busy_SameRoomType();
		scenarioBuilder.createDummyScenario();
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		assertTrue(alternatives.size() == 2);
		
	}
	
	@Test
	public void testRoom2_3Busy() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_Rooms_2_3_busy_SameRoomType scenarioBuilder = new ScenarioBuilder_Rooms_2_3_busy_SameRoomType();
		scenarioBuilder.createDummyScenario();
		
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		assertTrue(alternatives.size() == 1);
		
	}
	
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testNoRootAlternatives() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_NoRootAlternatives_SameRoomType scenarioBuilder = new ScenarioBuilder_NoRootAlternatives_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
				
	}
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testLessDaysThanExpected() throws DaoException, NoAvailableAlternativesException, NoRateException{
	
		ScenarioBuilder_LessDaysThanExpected_SameRoomType scenarioBuilder = new ScenarioBuilder_LessDaysThanExpected_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
				
	}
	
	
	/**
	 * room changes
	 */

	@Test
	public void testValidRoomChangeLastDay() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_ValidRoomChange_LastDay_SameRoomType scenarioBuilder = new ScenarioBuilder_ValidRoomChange_LastDay_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		assertTrue(alternatives.size() == 1);
		
		Alternative alternative = alternatives.get(0);
		System.out.println(alternative);
		
		assertTrue(alternative.getLastRoom().getNumber() == 1);
		assertTrue(alternative.getRoomChanges() == 1);
		
		int i = 1;
		for (Occupation occupation : alternative.getOccupations()) {
			if(i != 10){
				assertTrue(occupation.getId().getRoom().getNumber() == 2);
			}
			else{
				assertTrue(occupation.getId().getRoom().getNumber() == 1);
			}
			i++;
		}
				
	}
	
	@Test
	public void testValidRoomChangesTwoAlternatives() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_ValidRoomChangesTwoAlternatives_SameRoomType scenarioBuilder = new ScenarioBuilder_ValidRoomChangesTwoAlternatives_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		
		assertTrue(alternatives.size() == 2);
		
		Alternative alternative1 = alternatives.get(0);
		assertTrue(alternative1.getRoomChanges() == 1);
		
		Alternative alternative2 = alternatives.get(1);
		assertTrue(alternative2.getRoomChanges() == 2);
				
	}
	
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testInvalidRoomChangesTwoAlternatives() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_InvalidRoomChangesTwoAlternatives_SameRoomType scenarioBuilder = new ScenarioBuilder_InvalidRoomChangesTwoAlternatives_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		
	}
	
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testRoomChangesExceeded() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_RoomChangesExceeded_SameRoomType scenarioBuilder = new ScenarioBuilder_RoomChangesExceeded_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		
	}
	
	@Test(expected=NoAvailableAlternativesException.class)
	public void testInvalidRoomChangesFirstDay() throws DaoException, NoAvailableAlternativesException, NoRateException {
	
		ScenarioBuilder_InvalidRoomChanges_FirstDay_SameRoomType scenarioBuilder = new ScenarioBuilder_InvalidRoomChanges_FirstDay_SameRoomType(); 
		scenarioBuilder.createDummyScenario();
				
		GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
		Date dateFrom = calendar.getTime();
		
		calendar.add(Calendar.DATE, 9);
		
		Date dateTo = calendar.getTime();
		
		List<Alternative> alternatives = this.occupationService.checkAvailability(dateFrom, dateTo, 2, 2);
		
		for (Alternative alternative : alternatives) {
			System.out.println(alternative);
		}
		
	}
}

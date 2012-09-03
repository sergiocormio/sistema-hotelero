package com.cdz.sh.service.core;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.service.OccupationService;
import com.cdz.sh.service.ReservationFormService;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllEmpty_SameRoomType;
import com.cdz.sh.service.exception.NoAvailableAlternativesException;
import com.cdz.sh.service.impl.OccupationServiceImpl;
import com.cdz.sh.service.impl.ReservationFormServiceImpl;

public class TestCore_SameRoomType {

	private OccupationService occupationService;
	
	@Before
	public void setUp() throws Exception {
		this.occupationService = new OccupationServiceImpl();
	}

	
	@Test
	public void testAllEmpty() throws DaoException, NoAvailableAlternativesException {
	
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

}

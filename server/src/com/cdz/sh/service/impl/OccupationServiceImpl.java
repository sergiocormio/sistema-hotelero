package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.OccupationService;
import com.cdz.sh.util.DateUtil;

/**
 * Implementation of OccupationService facade
 * 
 * @author fede
 *
 */
public class OccupationServiceImpl extends AbstractCrudService<Occupation, OccupationPK> implements OccupationService {

	
	private OccupationDao occupationDao; 
	private RoomDao roomDao;
	

	public OccupationServiceImpl(){
		super();
		this.roomDao = new RoomDaoImpl();
	}
	
	@Override
	protected CrudDao<Occupation, OccupationPK> createDao() {
		// to be able to access specific occupationDao methods
		this.occupationDao = new OccupationDaoImpl();
				
		//to have CRUD operations
		return this.occupationDao;
	}
	
	

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException {
		return this.occupationDao.retrieveOccupations(dateFrom, dateTo);
	}

	@Override
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int peopleQty, int variance) throws DaoException {
		
		List<Room> roomsByCapacity = this.roomDao.retrieveRoomsByCapacity(peopleQty);
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(dateFrom, dateTo, peopleQty);
		
		List<Occupation> possibleOccupations = createPossibleOccupations(dateFrom, dateTo, roomsByCapacity, occupations);
		
		List<Alternative> alternatives = createAlternatives(possibleOccupations, dateFrom, dateTo);
		/*
		 * 	TODO
		 * 
		 *  add support for searching by other date ranges
		 * 
		 */
		return alternatives;
	}



	private List<Occupation> createPossibleOccupations(Date dateFrom, Date dateTo, List<Room> roomsByCapacity, List<Occupation> occupations) {
		List<Occupation> possibleOccupations = new ArrayList<Occupation>();
		Date date = dateFrom;
		while(!date.after(dateTo)){
			for (Room room : roomsByCapacity) {
				Occupation possibleOccupation = new Occupation(date, room);
				if(!occupations.contains(possibleOccupation)){
					possibleOccupations.add(possibleOccupation);
				}
			}
			date = DateUtil.getNextDay(date);
			
		}
		return possibleOccupations;
	}

	/**
	 * Bubble sort
	 * 
	 * @param alternatives
	 * @return
	 */
	private List<Alternative> sortAlternatives(List<Alternative> alternatives) {
		
		for(int i = 0; i < alternatives.size(); i++){
			for(int j = i; j < alternatives.size(); j++){
				Alternative alternativei = alternatives.get(i);
				Alternative alternativej = alternatives.get(j);
				
				if(alternativei.compareTo(alternativej) == 1){ // i > j
					alternatives.set(i, alternativej);
					alternatives.set(j, alternativei);
				}
			}
		}
		return alternatives;
	}



	private List<Alternative> createAlternatives(List<Occupation> occupations, Date dateFrom, Date dateTo) throws DaoException {
		
		Date date = dateFrom;
		
		List<Occupation> occupationsOfThisDate = getOccupationsByDate(occupations, date);
		List<Alternative> alternatives = buildRoothAlternatives(occupationsOfThisDate);
		
		while(!date.after(dateTo)){
			date = DateUtil.getNextDay(date);
			
			occupationsOfThisDate = getOccupationsByDate(occupations, date);
			
			alternatives = updateAlternatives(alternatives, occupationsOfThisDate);
		}
		// post validations and sort 
		alternatives = validateAlternatives(alternatives);
		alternatives = sortAlternatives(alternatives);
		
		return alternatives;
	}



	private List<Alternative> validateAlternatives(List<Alternative> alternatives) throws DaoException {
		List<Alternative> filteredAlternatives = new ArrayList<Alternative>();
		BudgetService budgetService = new BudgetServiceImpl();
		for (Alternative alternative : alternatives) {
			
			if(alternative.hasValidRoomChanges()){
				alternative.setBudget(budgetService.getBudget(alternative));
				filteredAlternatives.add(alternative);
			}
			
		}
		
		return filteredAlternatives;
	}

	

	private List<Alternative> updateAlternatives(List<Alternative> alternatives, List<Occupation> occupationsOfThisDate) {
		List<Alternative> updatedAlternatives = new ArrayList<Alternative>();
		
		for (Alternative alternative : alternatives) {
			for (Occupation occupation : occupationsOfThisDate) {
				Alternative clonedAlternative = alternative.clone();
				clonedAlternative.addOccupation(occupation);
				if(clonedAlternative.getRoomChanges() < 3){
					updatedAlternatives.add(clonedAlternative);
				}
			}
		}
		
		return updatedAlternatives;
	}



	private List<Alternative> buildRoothAlternatives(List<Occupation> occupationsOfThisDate) {
		List<Alternative> roothAlternatives = new ArrayList<Alternative>();
		
		for (Occupation occupation : occupationsOfThisDate) {
			Alternative alternative = new Alternative();
			alternative.addOccupation(occupation);
			roothAlternatives.add(alternative);
		}
		
		return roothAlternatives;
	}



	private List<Occupation> getOccupationsByDate(List<Occupation> occupations,	Date date) {
		
		List<Occupation> occupationsOfThisDate = new ArrayList<Occupation>();
		for (Occupation occupation : occupations) {
			Date occupationDate = occupation.getId().getDate();
			if(DateUtil.sameDate(occupationDate, date)){
				occupationsOfThisDate.add(occupation);
			}
		}
		return occupationsOfThisDate;
	}


	
	
	

}

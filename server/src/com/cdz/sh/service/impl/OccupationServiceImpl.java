package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.OccupationService;
import com.cdz.sh.service.exception.NoAvailableAlternativesException;
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
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int adultsQty, int childrenQty) throws DaoException, NoAvailableAlternativesException {
		
//		dateTo = DateUtil.getDateAboutToFinish(dateTo); 
				
		List<Room> roomsByCapacity = this.roomDao.retrieveRoomsByCapacity(adultsQty, childrenQty);
		
		List<Occupation> occupations = this.occupationDao.retrieveConfirmedOccupations(dateFrom, dateTo, adultsQty, childrenQty);
		
		List<Occupation> possibleOccupations = createPossibleOccupations(dateFrom, dateTo, roomsByCapacity, occupations);
		
		List<Alternative> alternatives = createAlternatives(possibleOccupations, dateFrom, dateTo);
		
		return alternatives;
	}



	/**
	 * Creates the list of possible occupations taking into account:
	 * 
	 * 		- room capacity
	 * 		- date range
	 * 		- room occupation
	 * 		- has at least one available room to occupate, for each date
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param roomsByCapacity
	 * @param occupations
	 * @return
	 * @throws NoAvailableAlternativesException 
	 */
	private List<Occupation> createPossibleOccupations(Date dateFrom, Date dateTo, List<Room> roomsByCapacity, List<Occupation> occupations) throws NoAvailableAlternativesException {
		List<Occupation> possibleOccupations = new ArrayList<Occupation>();
		Date date = dateFrom;
		
		boolean hasAtLeastOneAvailableOccupationForThisDate = true;
		
		while(!date.after(dateTo) && hasAtLeastOneAvailableOccupationForThisDate){
			
			hasAtLeastOneAvailableOccupationForThisDate = false;
			
			for (Room room : roomsByCapacity) {
				// part of the PK is null, but it does not matter, because it will not be persisted yet.
				Occupation possibleOccupation = new Occupation(date, room, null);
				if(!occupations.contains(possibleOccupation)){
					possibleOccupations.add(possibleOccupation);
					hasAtLeastOneAvailableOccupationForThisDate = true;
				}
			}
			if(hasAtLeastOneAvailableOccupationForThisDate){
				date = DateUtil.getNextDay(date);
			}
		}
		
		if(!hasAtLeastOneAvailableOccupationForThisDate){
			throw new NoAvailableAlternativesException("No available alternatives for date: " + DateUtil.dateToStringYYYYmmDD(date));
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



	private List<Alternative> createAlternatives(List<Occupation> possibleOccupations, Date dateFrom, Date dateTo) throws DaoException, NoAvailableAlternativesException {
		
		Date date = dateFrom;
		
		List<Occupation> occupationsOfThisDate = getOccupationsByDate(possibleOccupations, date);
		List<Alternative> alternatives = buildRoothAlternatives(occupationsOfThisDate);
		
		date = DateUtil.getNextDay(date);
		
		while(!date.after(dateTo) && !alternatives.isEmpty()){
			
			occupationsOfThisDate = getOccupationsByDate(possibleOccupations, date);
			
			alternatives = updateAlternatives(alternatives, occupationsOfThisDate);
			
			date = DateUtil.getNextDay(date);
		}
		if(alternatives.isEmpty()){
			throw new NoAvailableAlternativesException("No available alternatives");
		}
		// post operations after validations 
		alternatives = addBudgets(alternatives);
		alternatives = sortAlternatives(alternatives);
		
		return alternatives;
	}



	private List<Alternative> addBudgets(List<Alternative> alternatives) throws DaoException {
		BudgetService budgetService = new BudgetServiceImpl();
		for (Alternative alternative : alternatives) {
			alternative.setBudget(budgetService.getBudget(alternative));
		}
		return alternatives;
	}

	

	/**
	 * While it updates the list of alternatives, it also validates if:
	 * 
	 * 		- the number of room changes is less than 3
	 * 		- the room changes are valid (@see Alternative..hasValidRoomChanges() )
	 * 
	 * @param alternatives
	 * @param occupationsOfThisDate
	 * @return
	 */
	private List<Alternative> updateAlternatives(List<Alternative> alternatives, List<Occupation> occupationsOfThisDate) {
		List<Alternative> updatedAlternatives = new ArrayList<Alternative>();
		
		for (Alternative alternative : alternatives) {
			Room lastRoom = alternative.getLastRoom();
			Occupation occupationOnLastRoom = findOccupationOnLastRoom(occupationsOfThisDate, lastRoom);
			if(occupationOnLastRoom != null){
				Alternative clonedAlternative = alternative.clone();
				clonedAlternative.addOccupation(occupationOnLastRoom);
				updatedAlternatives.add(clonedAlternative);
			}
			else{
				for (Occupation occupation : occupationsOfThisDate) {
					Alternative clonedAlternative = alternative.clone();
					clonedAlternative.addOccupation(occupation);
					if(clonedAlternative.getRoomChanges() < 3 && clonedAlternative.hasValidRoomChanges()){
						updatedAlternatives.add(clonedAlternative);
					}
				}
			}
		}
		
		return updatedAlternatives;
	}


	

	private Occupation findOccupationOnLastRoom(List<Occupation> occupationsOfThisDate, Room lastRoom) {
		
		for (Occupation occupationOfThisDate : occupationsOfThisDate) {
			if(occupationOfThisDate.getId().getRoom().equals(lastRoom)){
				return occupationOfThisDate;
			}
		}
		return null;
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

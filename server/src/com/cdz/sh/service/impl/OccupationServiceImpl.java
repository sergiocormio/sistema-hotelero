package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.PromotionDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.PromotionDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.Promotion;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.request.CheckAvailabilityRequest;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.OccupationService;
import com.cdz.sh.service.exception.InvalidOperationException;
import com.cdz.sh.service.exception.NoAvailableAlternativesException;
import com.cdz.sh.service.exception.NoRateException;
import com.cdz.sh.trigger.CheckReservationFormsExpirationTrigger;
import com.cdz.sh.trigger.Trigger;
import com.cdz.sh.util.DateUtil;

/**
 * Implementation of OccupationService facade
 * 
 * @author fede
 *
 */
public class OccupationServiceImpl extends AbstractCrudService<Occupation, OccupationPK> implements OccupationService {

	
	private Trigger checkReservationFormsExpirationTrigger;
	private OccupationDao occupationDao; 
	private RoomDao roomDao;
	private PromotionDao promotionDao; 
	

	public OccupationServiceImpl(){
		super();
		this.roomDao = new RoomDaoImpl();
		this.promotionDao = new PromotionDaoImpl();
		
		this.checkReservationFormsExpirationTrigger = new CheckReservationFormsExpirationTrigger();
	}
	
	@Override
	protected CrudDao<Occupation, OccupationPK> createDao() {
		// to be able to access specific occupationDao methods
		this.occupationDao = new OccupationDaoImpl();
				
		//to have CRUD operations
		return this.occupationDao;
	}
	
	
	

	@Override
	public Occupation createRecord(Occupation e) throws DaoException, InvalidOperationException {
		throw new InvalidOperationException(ExceptionErrorCodes.INVALID_OPERATION, "Invalid operation. You should use the 'book' method");
	}

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		return this.occupationDao.retrieveOccupations(dateFrom, dateTo);
	}

	@Override
	public List<Alternative> checkAvailability(CheckAvailabilityRequest request) throws DaoException, NoAvailableAlternativesException, NoRateException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		List<Room> roomsByCapacity = this.roomDao.retrieveRoomsByCapacity(request.getAdultsQty(), request.getChildrenQty(), request.isWithMaritalBed());
		
//		GregorianCalendar calendar = new GregorianCalendar(); 
//		calendar.setTime(dateTo);
//		calendar.add(Calendar.DATE, 4);
//		Date dateToForCheckingValidRoomChanges = calendar.getTime();
		
		List<Occupation> occupations = this.occupationDao.retrieveConfirmedOccupations(request);
		
		List<Occupation> possibleOccupations = createPossibleOccupations(request.getDateFrom(), request.getDateTo(), roomsByCapacity, occupations);
		
		List<Alternative> alternatives = createAlternatives(possibleOccupations, request);
		
		// post operations after validations 
		// if we are here, this means there is at least one alternative that matches the specified filters.
		alternatives = addPromotions(alternatives);
		alternatives = addBudgets(alternatives);
		alternatives = sortAlternatives(alternatives);
		
		return alternatives;
	}



	private List<Alternative> addPromotions(List<Alternative> alternatives) throws DaoException, NoRateException {
		
		Date maxDateTo = DateUtil.getMaxDateTo(alternatives);
		Date minDateFrom = DateUtil.getMinDateFrom(alternatives);
		
		List<Promotion> promotions = this.promotionDao.retrieveContainedPromotions(minDateFrom, maxDateTo);
		
		if(promotions.isEmpty()){
			return alternatives;
		}
		
		PromotionServiceImpl promotionServiceImpl = new PromotionServiceImpl();
		for (Alternative alternative : alternatives) {
			
			alternative = promotionServiceImpl.checkPromotions(alternative, promotions);
		}
		
		return alternatives;
	}
	
	

	private List<Alternative> filterInvalidLastRoomChanges(List<Alternative> alternatives) throws DaoException {
		int i = 0;
		while(i < alternatives.size()){
			Alternative alternative = alternatives.get(i);
			if(alternative.getNewRoomAvailableDays() > 0 && !isLastRoomChangeValid(alternative)){
					alternatives.remove(i);
			}
			else{
				i++;
			}
		}
		return alternatives;
	}
	

	private boolean isLastRoomChangeValid(Alternative alternative) throws DaoException {
		
		Date nextDayAfterTheLastOneSelected = DateUtil.getNextDay(alternative.getDateTo());
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(nextDayAfterTheLastOneSelected);
		/*
		 * nextDay + maxDaysToAddToTheNextDay = 4 ==> if these for days are empty, the roomChange is INVALID!
		 */
		int maxDaysToAddToTheNextDay = 3;
		
		calendar.add(Calendar.DATE, maxDaysToAddToTheNextDay - alternative.getNewRoomAvailableDays());
		Date dateTo = calendar.getTime();
		
		Room lastRoom = alternative.getLastRoom();
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(nextDayAfterTheLastOneSelected, dateTo, lastRoom);
		if(occupations.isEmpty()){
			return false; //invalid room change	
		}
		else{
			return true; //valid room change
		}
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
			throw new NoAvailableAlternativesException(ExceptionErrorCodes.NO_AVAILABLE_OCCUPATIONS, "No available occupation for date: " + DateUtil.dateToStringYYYYmmDD(date));
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



	private List<Alternative> createAlternatives(List<Occupation> possibleOccupations, CheckAvailabilityRequest request)
															throws DaoException, NoAvailableAlternativesException, NoRateException {
		
		Date date = request.getDateFrom();
		
		List<Occupation> occupationsOfThisDate = getOccupationsByDate(possibleOccupations, date);
		List<Alternative> alternatives = buildRoothAlternatives(occupationsOfThisDate, request.getAdultsQty(), request.getChildrenQty());
		
		date = DateUtil.getNextDay(date);
		
		while(!date.after(request.getDateTo()) && !alternatives.isEmpty()){
			
			occupationsOfThisDate = getOccupationsByDate(possibleOccupations, date);
			
			alternatives = updateAlternatives(alternatives, occupationsOfThisDate);
			
			if(!alternatives.isEmpty()){
				date = DateUtil.getNextDay(date);
			}
		}
		
		if(alternatives.isEmpty()){
			throw new NoAvailableAlternativesException(ExceptionErrorCodes.NO_AVAILABLE_ALTERNATIVES, "No available alternatives for date: " + DateUtil.dateToStringYYYYmmDD(date));
		}
		
		alternatives = filterInvalidLastRoomChanges(alternatives);
		
		if(alternatives.isEmpty()){
			throw new NoAvailableAlternativesException(ExceptionErrorCodes.NO_AVAILABLE_ALTERNATIVES, "No available alternatives. All last room changes are not valid");
		}
	
		return alternatives;
	}



	private List<Alternative> addBudgets(List<Alternative> alternatives) throws DaoException, NoRateException {
		
		BudgetService budgetService = new BudgetServiceImpl();
		
		return budgetService.populateBudgets(alternatives);
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
				/*
				 * As I keep the same room, I do NOT have to validate roomChanges  
				 */
				if(clonedAlternative.hasValidRoomChanges()){
					updatedAlternatives.add(clonedAlternative);
				}
			}
			else{
				for (Occupation occupation : occupationsOfThisDate) {
					Alternative clonedAlternative = alternative.clone();
					clonedAlternative.addOccupation(occupation);
					/*
					 * As I've chosen a different room, I DO have to validate roomChanges  
					 */
					if(clonedAlternative.hasValidNumberOfRoomChanges()){
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

	private List<Alternative> buildRoothAlternatives(List<Occupation> occupationsOfThisDate, int adultsQty, int childrenQty) {
		List<Alternative> roothAlternatives = new ArrayList<Alternative>();
		
		for (Occupation occupation : occupationsOfThisDate) {
			Alternative alternative = new Alternative(adultsQty + childrenQty);
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


	@Override
	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		return this.occupationDao.retrieveOccupations(reservationForm);
	}
	
	

}

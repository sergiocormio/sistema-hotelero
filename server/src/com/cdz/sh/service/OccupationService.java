package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.request.CheckAvailabilityRequest;
import com.cdz.sh.service.exception.NoAvailableAlternativesException;
import com.cdz.sh.service.exception.NoRateException;


/**
 * Facade for Occupation entity
 * 
 * @author fede
 *
 */
public interface OccupationService extends CrudService<Occupation, OccupationPK>{
	
	
	/**
	 * Used for showing the state of each room, given a range of dates
	 *  
	 * @param fromDate
	 * @param toDate
	 * @return occupations ordered by date and roomNumber
	 */
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException;
	
	/**
	 * Retrieves a list a available alternatives order by priority (price, room changes, etc) <br />
	 * 
	 * 		THIS IS THE ===> << M A I N   C O R E >> <===
	 *  
	 * @param dateFrom
	 * @param dateTo
	 * @param peopleQty
	 * @param variance
	 * @return
	 * @throws NoRateException 
	 */
	public List<Alternative> checkAvailability(CheckAvailabilityRequest request) throws DaoException, NoAvailableAlternativesException, NoRateException;
	
	
	/**
	 * Retrieves occupations related to a given reservation form
	 * 
	 * @param reservationForm
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException;
}

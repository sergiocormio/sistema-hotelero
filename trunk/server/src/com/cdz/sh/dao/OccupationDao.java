package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.request.CheckAvailabilityRequest;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface OccupationDao extends CrudDao<Occupation, OccupationPK> {

	
	/**
	 * To be used to display current occupations (grid from main screen)
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException;
	
	
	/**
	 * To be used by the CORE algorithm
	 * 
	 * @param request
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveConfirmedOccupations(CheckAvailabilityRequest request) throws DaoException;

	
	/**
	 * To be used from Cleaning report 
	 * 
	 * @param date
	 * @throws DaoException
	 */
	public List<Occupation> retrieveConfirmedOccupations(Date date) throws DaoException;
	
	/**
	 * To be used from Cleaning report 
	 * (Should be no more than one Occupation in the returned list)
	 * @param date,room
	 * @throws DaoException
	 */
	public List<Occupation> retrieveConfirmedOccupations(Date date,Room room) throws DaoException;
	
	/**
	 * To be used for canceling overlaped occupations when updateing a reservation form
	 * 
	 * @param reservationForm
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException;

	/**
	 * Retrieves overlaped occupations
	 * 
	 * @param occupation
	 * @param reservationForm
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveOverlapedOccupations(Occupation occupation, ReservationForm reservationForm) throws DaoException;

	/**
	 * Used for checking valid last day of a room change 
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param room
	 * @return
	 * @throws DaoException
	 */
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo, Room room) throws DaoException;



}

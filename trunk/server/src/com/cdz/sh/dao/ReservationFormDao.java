package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface ReservationFormDao extends CrudDao<ReservationForm, Long> {

	
	/**
	 * Retrieves reservation forms given a range of dates and/or a selected customer and/or a given state.
	 * All fields could be optional however at least one of them must be mandatory.
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param customer
	 * @param state
	 * @return
	 */
	public List<ReservationForm> retrieveReservationForms(Date dateFrom, Date dateTo, Customer customer, StateReservationForm state) throws InvalidParameterException, DaoException;
	
	
	public List<ReservationForm> retrieveActiveReservationForms(Date date, Room room) throws DaoException;

	
	public List<ReservationForm> retrieveActiveReservationForms(Customer customer) throws DaoException;
	
	
	public List<ReservationForm> retrieveActiveReservationForms() throws DaoException;
	
	

}

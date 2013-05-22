package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;

/**
 * Facade for ReservationForm entity
 * 
 * @author fede
 *
 */
public interface ReservationFormService extends CrudService<ReservationForm, Long>{
	
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
	public List<ReservationForm> retrieveReservationForms(Date dateFrom, Date dateTo, Customer customer, StateReservationForm state) 
			throws InvalidParameterException, DaoException;
	
	/**
	 * Books a room or set of rooms related to the given range of dates. In other words, creates the ReservationForm
	 * and all related occupations. 
	 * When the user choose an alternative, it will be displayed the new reservationForm with some fields automatically filled.
	 * The remaining ones have to be filled by the user who is going to create the reservation form (e.g: client from a combo-box).
	 * Finally when the user clicks "create", this method will be invoked. 
	 * 
	 * @param chosenAlternative
	 * @param reservationForm
	 * @return
	 */
	public ReservationForm book(Alternative chosenAlternative, ReservationForm reservationForm) throws DaoException, InvalidParameterException;
	
	
	/**
	 * Export to PDF the information given
	 * 
	 * @param reservationForm
	 * @return the absolute path to the report generated
	 */
	public byte[] exportData(ReservationForm reservationForm, String selectedLocale) throws DaoException;
	
	
	public List<ReservationForm> retrieveActiveReservationFormsByDateAndRoom(Date date, Room room) throws DaoException;
	
	
	public List<ReservationForm> retrieveActiveReservationFormsByCustomer(Customer customer) throws DaoException;
	
	
	public List<ReservationForm> retrieveActiveReservationForms() throws DaoException;
	
}

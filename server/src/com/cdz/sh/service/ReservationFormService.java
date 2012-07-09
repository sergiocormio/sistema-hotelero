package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.ReservationForm;

/**
 * Facade for ReservationForm entity
 * 
 * @author fede
 *
 */
public interface ReservationFormService extends CrudService<ReservationForm, Long>{
	
	/**
	 * retrieves reservation forms given a range of dates. ReservationFormNumber is optional
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param reservationFormNumber
	 * @return
	 */
	public List<ReservationForm> retrieveReservationForms(Date dateFrom, Date dateTo, int reservationFormNumber);
	
	/**
	 * Books a room or set of rooms related to the given range of dates. In other words, creates the ReservationForm
	 * and all related occupations with the chosenAlternative data.
	 * 
	 * @param chosenAlternative
	 * @return
	 */
	public boolean book(Alternative chosenAlternative);
	
	
	/**
	 * Export to PDF the information given
	 * 
	 * @param reservationForm
	 * @return the absolute path to the report generated
	 */
	public String exportData(ReservationForm reservationForm);
}

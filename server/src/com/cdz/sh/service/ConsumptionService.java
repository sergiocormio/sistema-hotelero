package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Room;

/**
 * Facade for Consumption entity
 * 
 * @author fede
 *
 */
public interface ConsumptionService extends CrudService<Consumption, Long>{
	
	
	/**
	 * Retrieves a list of consumptions realted to a given range of dates
	 * 
	 * @param DateFrom
	 * @param dateTo
	 * @return
	 */
	public List<Consumption> retrieveConsumptions(Date DateFrom, Date dateTo, Room room) throws DaoException;

}

package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Room;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface ConsumptionDao extends CrudDao<Consumption, Long> {


	/**
	 * Retrieves a list of consumptions related to a given range of dates
	 * @param dateFrom
	 * @param dateTo
	 * @param room 
	 * @return
	 */
	public List<Consumption> retrieveConsumptions(Date dateFrom, Date dateTo, Room room) throws DaoException;
	
}

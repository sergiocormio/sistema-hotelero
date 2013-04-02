package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Cleaning;

/**
 * Facade for Cleaning entity
 * 
 * @author fede
 *
 */
public interface CleaningService extends CrudService<Cleaning, Long>{
	
	
	/**
	 * Retrieves a list a rooms to be cleaned according to the given date
	 * 
	 * @param date
	 * @return
	 */
	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException;
	
	/**
	 * Export to PDF the information given
	 * 
	 * @param cleanings
	 * @return the absolute path to the report generated
	 */
	public byte[] exportData(List<Cleaning> cleanings, String selectecLocale);

}

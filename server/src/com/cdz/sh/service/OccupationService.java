package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;


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
	 * @return
	 */
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo);
	
	/**
	 * Retrieves a list a available alternatives order by priority (price, room changes, etc)
	 *  
	 * @param dateFrom
	 * @param dateTo
	 * @param peopleQty
	 * @param variance
	 * @return
	 */
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int peopleQty, int variance);
	
	

}

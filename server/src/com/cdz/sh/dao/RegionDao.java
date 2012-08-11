package com.cdz.sh.dao;

import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Region;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface RegionDao extends CrudDao<Region, Long> {

	
	/**
	 * Retrieves all regions related to a given country
	 * 
	 * @param country
	 * @return
	 * @throws DaoException
	 */
	public List<Region> retrieveRegions(Country country) throws DaoException;

	

}

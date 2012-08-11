package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Region;

/**
 * Facade for Region entity
 * 
 * @author fede
 *
 */
public interface RegionService extends CrudService<Region, Long>{
	
	
	public List<Region> retrieveRegions(Country country) throws DaoException;

}

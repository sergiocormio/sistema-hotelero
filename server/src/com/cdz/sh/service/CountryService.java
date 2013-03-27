package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;

/**
 * Facade for Country entity
 * 
 * @author fede
 *
 */
public interface CountryService extends CrudService<Country, Long>{
	
	
	public List<Country> retrieveCountriesWithoutRegions() throws DaoException;
}

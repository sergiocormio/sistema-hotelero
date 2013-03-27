package com.cdz.sh.service.impl;

import java.util.List;

import com.cdz.sh.dao.CountryDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.model.Country;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CountryService;

/**
 * Implementation of CountryService facade
 * 
 * @author fede
 *
 */
public class CountryServiceImpl extends AbstractCrudService<Country, Long> implements CountryService {

	private CountryDao countryDao;
	
	@Override
	protected CrudDao<Country, Long> createDao() {
		this.countryDao = new CountryDaoImpl();
		return this.countryDao;
	}

	@Override
	public List<Country> retrieveCountriesWithoutRegions() throws DaoException {
		return this.countryDao.retrieveCountriesWithoutRegions();
	}
	

}

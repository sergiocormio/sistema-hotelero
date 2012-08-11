package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
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

	@Override
	protected CrudDao<Country, Long> createDao() {
		return new CountryDaoImpl();
	}
	

}

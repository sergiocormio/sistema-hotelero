package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ExchangeRateService;

/**
 * Implementation of ExchangeRateService facade
 * 
 * @author fede
 *
 */
public class ExchangeRateServiceImpl extends AbstractCrudService<ExchangeRate, String> implements ExchangeRateService {


	@Override
	protected CrudDao<ExchangeRate, String> createDao() {
		return new ExchangeRateDaoImpl();
	}

	
	
	

}

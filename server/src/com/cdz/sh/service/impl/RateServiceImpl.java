package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RatePK;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.RateService;

/**
 * Implementation of RateService facade
 * 
 * @author fede
 *
 */
public class RateServiceImpl extends AbstractCrudService<Rate, RatePK> implements RateService {

	@Override
	protected CrudDao<Rate, RatePK> createDao() {
		return new RateDaoImpl();
	}

	
	
	

}

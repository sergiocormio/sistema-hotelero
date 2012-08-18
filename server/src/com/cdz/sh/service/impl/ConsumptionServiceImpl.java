package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ConsumptionDaoImpl;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ConsumptionService;

/**
 * Implementation of ConsumptionService facade
 * 
 * @author fede
 *
 */
public class ConsumptionServiceImpl extends AbstractCrudService<Consumption, Long> implements ConsumptionService {

	
	@Override
	protected CrudDao<Consumption, Long> createDao() {
		return new ConsumptionDaoImpl();
	}
	
	
	

}

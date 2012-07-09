package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ServiceTypeService;

/**
 * Implementation of ServiceTypeService facade
 * 
 * @author fede
 *
 */
public class ServiceTypeServiceImpl extends AbstractCrudService<ServiceType, Long> implements ServiceTypeService {

	@Override
	protected CrudDao<ServiceType, Long> createDao() {
		return new ServiceTypeDaoImpl();
	}

	
	
	

}

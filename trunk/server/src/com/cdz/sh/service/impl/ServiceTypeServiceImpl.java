package com.cdz.sh.service.impl;

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

	public ServiceTypeServiceImpl() {
		this.crudDao = new ServiceTypeDaoImpl();
	}

	
	
	

}

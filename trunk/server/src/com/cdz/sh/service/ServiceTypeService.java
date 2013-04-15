package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ServiceType;

/**
 * Facade for ServiceType entity
 * 
 * @author fede
 *
 */
public interface ServiceTypeService extends CrudService<ServiceType, Long>{
	
	
	public List<ServiceType> getTransferServiceTypes() throws DaoException;

}

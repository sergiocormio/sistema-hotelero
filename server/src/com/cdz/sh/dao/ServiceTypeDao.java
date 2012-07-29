package com.cdz.sh.dao;

import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ServiceType;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface ServiceTypeDao extends CrudDao<ServiceType, Long> {

	public List<ServiceType> retrieveAdditionalServices() throws DaoException;

	
}

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

	/**
	 * ServiceType:
	 * 
	 *  "includedInBudget" == TRUE
	 *	"includedInBasePrice" == FALSE
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<ServiceType> retrieveAdditionalServices() throws DaoException;
	
	
	/**
	 * ServiceType:
	 * 
	 *	"includedInBudget" == TRUE
	 *	"includedInBasePrice" == TRUE 
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<ServiceType> retrieveServicesIncludedInBasePrice() throws DaoException;

	
}

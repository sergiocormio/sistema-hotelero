package com.cdz.sh.dao;

import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Region;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface CustomerDao extends CrudDao<Customer, Long> {

	
	public List<Customer> retrieveCustomers(List<Region> regions, boolean includeCustomersWithoutRegion) throws DaoException;

	

}

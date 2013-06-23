package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Region;

/**
 * Facade for Customer entity
 * 
 * @author fede
 *
 */
public interface CustomerService extends CrudService<Customer, Long>{
	
	

	public List<Customer> retrieveCustomers(List<Region> region, List<Integer> months) throws DaoException;
}

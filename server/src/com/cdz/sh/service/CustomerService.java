package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;

/**
 * Facade for Customer entity
 * 
 * @author fede
 *
 */
public interface CustomerService extends CrudService<Customer, CustomerPK>{
	
	

	public List<Customer> retrieveCustomers(List<Country> countries) throws DaoException;
}

package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Region;

/**
 * Facade for Customer entity
 * 
 * @author fede
 *
 */
public interface CustomerService extends CrudService<Customer, Long>{
	
	

	public List<Customer> retrieveCustomers(String email, String firstName, String lastName, Country country) throws InvalidParameterException, DaoException;
	
	public List<Customer> retrieveCustomers(List<Region> region, List<Integer> months,
											boolean includeCustomersWithoutRegion,
											boolean includeCustomersWithoutBirthdate) throws DaoException;
}

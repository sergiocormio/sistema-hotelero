package com.cdz.sh.service;

import java.util.Collection;

import com.cdz.sh.model.Customer;

/**
 * Facade for Customer entity
 * 
 * @author fede
 *
 */
public interface CustomerService extends CrudService<Customer, Long>{
	
	
	public Collection<Customer> retrieveAll();

}

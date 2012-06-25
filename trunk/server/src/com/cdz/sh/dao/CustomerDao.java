package com.cdz.sh.dao;

import java.util.Collection;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.model.Customer;

/**
 * Declares specific functionality for customers access data, in adittion to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface CustomerDao extends CrudDao<Customer, Long> {

	Collection<Customer> retrieveAll();

}

package com.cdz.sh.service.impl;

import java.util.Collection;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.service.CustomerService;
/**
 * Implementation of CustomerService facade
 * 
 * @author fede
 *
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	
	public CustomerServiceImpl(){
		this.customerDao = new CustomerDaoImpl();
	}

	@Override
	public void createRecord(Customer customer) {
		this.customerDao.createRecord(customer);
	}

	@Override
	public void updateRecord(Customer customer) {
		this.customerDao.updateRecord(customer);
	}

	@Override
	public void deleteRecord(Customer customer) {
		this.customerDao.deleteRecord(customer);
	}

	@Override
	public Customer getRecordById(CustomerPK id) {
		return this.customerDao.getRecordById(id);
	}

	@Override
	public Collection<Customer> retrieveAll() {
		return this.customerDao.retrieveAll();
	}

}

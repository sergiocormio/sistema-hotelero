package com.cdz.sh.service.impl;

import java.util.List;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CustomerService;

/**
 * Implementation of CustomerService facade
 * 
 * @author fede
 *
 */
public class CustomerServiceImpl extends AbstractCrudService<Customer, CustomerPK> implements CustomerService {

	private CustomerDao customerDao;
	
	@Override
	protected CrudDao<Customer, CustomerPK> createDao() {
		this.customerDao = new CustomerDaoImpl();
		return customerDao;
	}

	@Override
	public List<Customer> retrieveCustomers(List<Country> countries) throws DaoException {
		return this.customerDao.retrieveCustomers(countries);
	}

	

	
	
	

}

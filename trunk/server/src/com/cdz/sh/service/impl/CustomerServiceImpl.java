package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
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

	@Override
	protected CrudDao<Customer, CustomerPK> createDao() {
		return new CustomerDaoImpl();
	}

	

	
	
	

}

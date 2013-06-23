package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Region;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CustomerService;

/**
 * Implementation of CustomerService facade
 * 
 * @author fede
 *
 */
public class CustomerServiceImpl extends AbstractCrudService<Customer, Long> implements CustomerService {

	private CustomerDao customerDao;
	
	@Override
	protected CrudDao<Customer, Long> createDao() {
		this.customerDao = new CustomerDaoImpl();
		return customerDao;
	}

	@Override
	public List<Customer> retrieveCustomers(List<Region> regions, List<Integer> months) throws DaoException {
		
		List<Customer> customersByRegion = this.customerDao.retrieveCustomers(regions);
		
		List<Customer> customers = new ArrayList<Customer>();
		
		Calendar calendar = Calendar.getInstance();
		for (Customer customer : customersByRegion) {
			
			Date dateOfBirth = customer.getDateOfBirth();
			if(dateOfBirth == null){
				customers.add(customer);
			}
			else{
				calendar.setTime(dateOfBirth);
				
				if(months.contains(calendar.get(Calendar.MONTH))){
					customers.add(customer);
				}
			}
		}
		
		return customers;
	}

	

	
	
	

}

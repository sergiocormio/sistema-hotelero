package com.cdz.sh.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.model.Customer;
import com.cdz.sh.service.impl.CustomerServiceImpl;

public class TestCustomerServiceRetrieveAll {

	private CustomerService customerService;
	

	@Before
	public void setUp() throws Exception {
		this.customerService = new CustomerServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void retrieveAllCustomers() {
		/**
		 * esto solo fue para ver si andaba bien el retrieve
		 */
		Collection<Customer> customers = this.customerService.retrieveAll();
		for (Customer customer : customers) {
			System.out.println(customer.toString());	
		}
		//assertEquals(2, customers.size() );
	}

}

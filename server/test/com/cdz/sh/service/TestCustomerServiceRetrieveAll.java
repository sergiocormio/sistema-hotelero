package com.cdz.sh.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.impl.CustomerDaoImpl;
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
		assertEquals(2, this.customerService.retrieveAll().size() );
	}

}

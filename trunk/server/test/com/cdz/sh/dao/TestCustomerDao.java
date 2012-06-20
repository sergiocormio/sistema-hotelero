package com.cdz.sh.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.model.Customer;

public class TestCustomerDao {

	private CustomerDao customerDao;
	
	@Before
	public void setUp() throws Exception {
		this.customerDao = new CustomerDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateUpdateCustomers() {
		Customer c1 = new Customer();
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Customer c2 = new Customer();
		c2.setFirstName("Sergio");
		c2.setLastName("Cormio");
		c2.setDateOfBirth(new Date());

		this.customerDao.createRecord(c1);
		this.customerDao.createRecord(c2);
		
		Customer c1Found = this.customerDao.getRecordById(1L);
		assertNotNull(c1Found);
		Customer c2Found = this.customerDao.getRecordById(2L);
		assertNotNull(c2Found);
		
		c2Found.setFirstName("Sergio Adrian");
		
		this.customerDao.updateRecord(c2Found);
		
		Customer c2FoundWithNewFirstName = this.customerDao.getRecordById(2L);
		assertNotNull(c2FoundWithNewFirstName);
		assertEquals("Sergio Adrian", c2FoundWithNewFirstName.getFirstName());
		
	
	}
	
	/**
	 * to check if the customers created on the test above, were properly persisted on DB
	 */
	@Test
	public void testFindDeleteCustomers() {
		
		Customer c1Found = this.customerDao.getRecordById(1L);
		assertNotNull(c1Found);
		Customer c2Found = this.customerDao.getRecordById(2L);
		assertNotNull(c2Found);
		
		System.out.println("First Name: " + c1Found.getFirstName());
		System.out.println("Last Name: " + c1Found.getLastName());
		System.out.println("Date of Birth: " + c1Found.getDateOfBirth());
		System.out.println();
		System.out.println("First Name: " + c2Found.getFirstName());
		System.out.println("Last Name: " + c2Found.getLastName());
		System.out.println("Date of Birth: " + c2Found.getDateOfBirth());
		
		this.customerDao.deleteRecord(c2Found);
		this.customerDao.deleteRecord(c1Found);
		
		Customer c1NotFound = this.customerDao.getRecordById(1L);
		assertNull(c1NotFound);
		Customer c2NotFound = this.customerDao.getRecordById(2L);
		assertNull(c2NotFound);	
	}


}

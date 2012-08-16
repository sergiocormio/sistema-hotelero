package com.cdz.sh.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Language;
import com.cdz.sh.model.Region;

public class TestCustomerDao {

	private CustomerDao customerDao;
	
	private CustomerPK customerPK1;
	private CustomerPK customerPK2;
	
	private DocumentTypeDaoImpl documentTypeDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	
	private Customer c1;
	private Customer c2;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();		
		
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		this.customerDao = new CustomerDaoImpl();

				
		this.customerPK1 = new CustomerPK();
		this.customerPK1.setDocType(docTypeDNI);
		this.customerPK1.setIdNumber("33333333");
		
		this.customerPK2 = new CustomerPK();
		this.customerPK2.setDocType(docTypeDNI);
		this.customerPK2.setIdNumber("32XXXXXX");
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCRUD() throws DaoException {
		
		this.testCreateUpdateCustomers();
		
		this.testFindDeleteCustomers();
	}
	
	
	private void testCreateUpdateCustomers() throws DaoException {
		/**
		 * customer 1: Fede
		 */
		c1 = new Customer();
		c1.setId(customerPK1);
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Region region = this.regionDao.getRecordById(1L);
		c1.setRegion(region);
		
		Language language = this.languageDao.getRecordById(1L);
		c1.setLanguage(language);
		
		/**
		 * customer 2: Sergio
		 */
			
		c2 = new Customer();
		c2.setId(customerPK2);
		c2.setFirstName("Sergio");
		c2.setLastName("Cormio");
		c2.setDateOfBirth(new Date());
		
		c2.setLanguage(language);
		
		this.customerDao.createRecord(c1);
		this.customerDao.createRecord(c2);
		
		
		Customer c1Found = this.customerDao.getRecordById(customerPK1);
		assertNotNull(c1Found);
		Customer c2Found = this.customerDao.getRecordById(customerPK2);
		assertNotNull(c2Found);
		
		/**
		 * print
		 */
		System.out.println(c1Found.toString());
		System.out.println(c2Found.toString());
		
		c2Found.setFirstName("Sergio Adrian");
		
		this.customerDao.updateRecord(c2Found);
		
		Customer c2FoundWithNewFirstName = this.customerDao.getRecordById(customerPK2);
		assertNotNull(c2FoundWithNewFirstName);
		assertEquals("Sergio Adrian", c2FoundWithNewFirstName.getFirstName());
		
	
	}
	
	/**
	 * to check if the customers created on the test above, were properly persisted on DB
	 * @throws DaoException 
	 */
	private void testFindDeleteCustomers() throws DaoException {
		
		Customer c1Found = this.customerDao.getRecordById(this.customerPK1);
		assertNotNull(c1Found);
		Customer c2Found = this.customerDao.getRecordById(this.customerPK2);
		assertNotNull(c2Found);
		
		System.out.println("First Name: " + c1Found.getFirstName());
		System.out.println("Last Name: " + c1Found.getLastName());
		System.out.println("Date of Birth: " + c1Found.getDateOfBirth());
		System.out.println();
		System.out.println("First Name: " + c2Found.getFirstName());
		System.out.println("Last Name: " + c2Found.getLastName());
		System.out.println("Date of Birth: " + c2Found.getDateOfBirth());
		
		/***********/
		
		Customer customerToDelete1 = new Customer();
		customerToDelete1.setId(this.customerPK1);
		Customer customerToDelete2 = new Customer();
		customerToDelete2.setId(this.customerPK2);
		
		this.customerDao.deleteRecord(customerToDelete1);
		this.customerDao.deleteRecord(customerToDelete2);
		
		Customer c1NotFound = this.customerDao.getRecordById(this.customerPK1);
		assertNull(c1NotFound);
		Customer c2NotFound = this.customerDao.getRecordById(this.customerPK2);
		assertNull(c2NotFound);	
		
		//check that the DocumentType was not deleted
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		assertNotNull(docTypeDNI);
	}


}

package com.cdz.sh.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Region;

public class TestDocumentTypeDao {

	private DocumentTypeDaoImpl documentTypeDao;
	private CustomerDao customerDao;
	
	
	@Before
	public void setUp() throws Exception {
		
//		MasterDataFactory dataFactory = new MasterDataFactory();
//		dataFactory.createMasterData();		
		
		documentTypeDao = new DocumentTypeDaoImpl();
		customerDao = new CustomerDaoImpl();
	}


	@Test
	public void testExceptionRetrieved() {
		
		
		
		CustomerPK customerPK = new CustomerPK();
		customerPK.setDocType(null);
		customerPK.setIdNumber("33103189");
		
		Customer c1 = new Customer();
		c1.setCustomerPK(customerPK);
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		
//		DocumentType documentType = new DocumentType();
//		/*
//		 *  Sets the ID so as to throw a DaoException on the first catch
//		 *  This is to see if the first Tx was rolled back successfully or not (it should...)
//		 */
//		documentType.setId(0L);
//		
//		documentType.setName("DNI");
//		documentType.setRegExp("*");
		
		try{
			customerDao.createRecord(c1);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		try{
			Collection<DocumentType> docTypes = documentTypeDao.retrieveAll();
			assertTrue(docTypes.isEmpty());
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	

}

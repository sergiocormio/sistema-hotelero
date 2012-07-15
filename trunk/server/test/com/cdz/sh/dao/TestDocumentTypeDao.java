package com.cdz.sh.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;

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
	public void testGetGeneratedID() throws DaoException {
		
		DocumentType documentType = new DocumentType();
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		this.documentTypeDao.createRecord(documentType);
		
		assertEquals(documentType.getId(), new Long(1L));
			
		this.documentTypeDao.deleteRecord(documentType);
	}
	
	@Test
	public void testExceptionRetrieved() {
		
		
		
		CustomerPK customerPK1 = new CustomerPK();
		customerPK1.setDocType(null);
		customerPK1.setIdNumber("33103189");
		
		CustomerPK customerPK2 = new CustomerPK();
		customerPK2.setDocType(null);
		customerPK2.setIdNumber("33103189");
		
		Customer c1 = new Customer();
		c1.setCustomerPK(customerPK1);
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Customer c2 = new Customer();
		c2.setCustomerPK(customerPK1);
		c2.setFirstName("Federico");
		c2.setLastName("De Seta");
		c2.setDateOfBirth(new Date());
		
		try{
			customerDao.createRecord(c1);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		DocumentType documentType = new DocumentType();
		/*
		 *  Sets the ID so as to throw a DaoException on the first catch
		 *  This is to see if the first Tx was rolled back successfully or not (it should...)
		 */
		//documentType.setId(0L);
		
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		
		try{
			documentTypeDao.createRecord(documentType);
			DocumentType documentType2 = documentTypeDao.getRecordById(1L);
			
			customerPK1.setDocType(documentType2);
			c1.setCustomerPK(customerPK1);
			
			customerPK2.setDocType(documentType2);
			c2.setCustomerPK(customerPK2);
			
			customerDao.createRecord(c1);
			
			customerDao.createRecord(c2);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	

}

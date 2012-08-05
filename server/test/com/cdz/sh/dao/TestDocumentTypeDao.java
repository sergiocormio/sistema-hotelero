package com.cdz.sh.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
	public void testFindSuccessfully() throws DaoException{
		DocumentType documentType = new DocumentType();
//		documentType.setId(999L);
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		this.documentTypeDao.createRecord(documentType);
				
		DocumentType docFound = this.documentTypeDao.getRecordById(documentType.getId());
		
		assertEquals(documentType.getName(), docFound.getName());
		assertEquals(documentType.getRegExp(), docFound.getRegExp());
		assertEquals(documentType.getId(), docFound.getId());
				
	}
	
	@Test
	public void testFindNotExist() throws DaoException{
		DocumentType documentType = this.documentTypeDao.getRecordById(345L);
		assertNull(documentType);
	}
	
	@Test
	public void testUpdateSuccessfully() throws DaoException{
		DocumentType documentType = new DocumentType();
//		documentType.setId(999L);
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		DocumentType docCreated = this.documentTypeDao.createRecord(documentType);
		
		documentType.setRegExp("lala");
		this.documentTypeDao.updateRecord(documentType);
		
		DocumentType docFound = this.documentTypeDao.getRecordById(docCreated.getId());
		
		assertTrue(documentType.getName().equals(docFound.getName()));
		assertTrue(documentType.getRegExp().equals(docFound.getRegExp()));
		assertEquals(documentType.getId(), docFound.getId());
	}
	
	@Test(expected=DaoException.class)
	public void testUpdateNotExist() throws DaoException {
		
		DocumentType documentType = new DocumentType();
		documentType.setId(999L);
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		this.documentTypeDao.updateRecord(documentType);
		
	}
	
	@Test
	public void testDeleteSuccessfully() throws DaoException{
		DocumentType documentType = new DocumentType();
//		documentType.setId(999L);
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		this.documentTypeDao.createRecord(documentType);
		Long idToFind = documentType.getId();
		
		this.documentTypeDao.deleteRecord(documentType);
		
		DocumentType docFound = this.documentTypeDao.getRecordById(idToFind);
		assertNull(docFound);
				
	}
	
	@Test(expected=DaoException.class)
	public void testDeleteNotExist() throws DaoException{
		DocumentType documentType = new DocumentType();
		documentType.setId(999L);
		documentType.setName("DNI");
		documentType.setRegExp("*");
		this.documentTypeDao.deleteRecord(documentType);
	}
	
	
	@Test
	public void testGetGeneratedID() throws DaoException {
		
		DocumentType documentType = new DocumentType();
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		this.documentTypeDao.createRecord(documentType);
		
		assertTrue(documentType.getId().longValue() > 0 );
			
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
		c1.setId(customerPK1);
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Customer c2 = new Customer();
		c2.setId(customerPK1);
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
			c1.setId(customerPK1);
			
			customerPK2.setDocType(documentType2);
			c2.setId(customerPK2);
			
			customerDao.createRecord(c1);
			
			customerDao.createRecord(c2);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	

}

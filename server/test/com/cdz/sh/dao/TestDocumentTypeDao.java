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
	
	
	@Before
	public void setUp() throws Exception {
		
//		MasterDataFactory dataFactory = new MasterDataFactory();
//		dataFactory.createMasterData();		
		
		documentTypeDao = new DocumentTypeDaoImpl();
	}


	@Test
	public void testExceptionRetrieved() {
		
		DocumentType documentType = new DocumentType();
		/*
		 *  Sets the ID so as to throw a DaoException on the first catch
		 *  This is to see if the first Tx was rolled back successfully or not (it should...)
		 */
		documentType.setId(0L);
		
		documentType.setName("DNI");
		documentType.setRegExp("*");
		
		try{
			documentTypeDao.createRecord(documentType);
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

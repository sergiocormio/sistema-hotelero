package com.cdz.sh.migration;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.DocumentTypeDao;
import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.crud.EntityManagerSingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.service.MigrationService;
import com.cdz.sh.service.impl.MigrationServiceImpl;


/**
 * 
 * 
 * @author fede
 *
 */
public class TestMigrationServiceZipUtil {

	private MigrationService migrationService;
	private DocumentTypeDao documentTypeDao;
	
	private static final String BACKUP_PATH = "c:/mybackups/backup.zip";
	
	@Before
	public void setUp() throws Exception {
		
		this.documentTypeDao = new DocumentTypeDaoImpl();
		
		this.migrationService = new MigrationServiceImpl();
		
		/**
		 * need to clear DB before each test from this file
		 */
		EntityManagerSingleton.shutDown();
		EntityManagerSingleton.getInstance();
	}
	
	
	
	@Test
	public void testBackup() {
		
		EntityManagerSingleton.shutDown();
		EntityManagerSingleton.getInstance();
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();
		
		this.migrationService.backupDatabase(BACKUP_PATH);
		
	}


	
	@Test
	public void testRestoreCurrentDBEmpty() throws DaoException, SQLException {

		EntityManagerSingleton.shutDown();
		EntityManagerSingleton.getInstance();
		assertTrue(this.documentTypeDao.retrieveAll().size() == 0);
	
		this.migrationService.restoreDatabase(BACKUP_PATH);
		
		Collection<DocumentType> restored = this.documentTypeDao.retrieveAll();
		assertTrue(restored.size() == 3);
	}
	
	
	@Test
	public void testRestoreOverwriteExistingData() throws DaoException, SQLException {
				
		
		EntityManagerSingleton.shutDown();
		EntityManagerSingleton.getInstance();
		
		assertTrue(this.documentTypeDao.retrieveAll().size() == 0);
		
		createFourDocumentTypes();
		assertTrue(this.documentTypeDao.retrieveAll().size() == 4);
		
		this.migrationService.restoreDatabase(BACKUP_PATH);
		
		Collection<DocumentType> restored = this.documentTypeDao.retrieveAll();
		assertTrue(restored.size() == 3);
		
	}
	

	
	private void createFourDocumentTypes() throws DaoException {
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		if(docTypeDNI == null){
			docTypeDNI = new DocumentType();
			docTypeDNI.setName("11111");
			docTypeDNI.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeDNI);
		}
		DocumentType docTypeRG = this.documentTypeDao.getRecordById(2L);
		if(docTypeRG == null){
			docTypeRG = new DocumentType();
			docTypeRG.setName("22222");
			docTypeRG.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeRG);
		}
		DocumentType docTypeSSN = this.documentTypeDao.getRecordById(3L);
		if(docTypeSSN == null){
			docTypeSSN = new DocumentType();
			docTypeSSN.setName("3333");
			docTypeSSN.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeSSN);
		}
		DocumentType docType4 = this.documentTypeDao.getRecordById(4L);
		if(docType4 == null){
			docType4 = new DocumentType();
			docType4.setName("4444");
			docType4.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docType4);
		}
		
		
	}
}

package com.cdz.sh.migration;

import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.service.MigrationService;
import com.cdz.sh.service.impl.MigrationServiceImpl;

public class TestMigrationService {

	private MigrationService migrationService;
	
	@Before
	public void setUp() throws Exception {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();
		
		this.migrationService = new MigrationServiceImpl();
	}
	
	
	@Test
	public void testBackup() {
		
		this.migrationService.backupDatabase("c:/mybackups/2012-07-18");
		
	}

	
//	@Test
//	public void testRestore() {
//		EntityManager entityManager = EntityManagerSingleton.getInstance();
//		entityManager.getTransaction().begin();
//		DocumentType docFound = entityManager.find(DocumentType.class, 1L);
//		assertNotNull(docFound);
//		entityManager.getTransaction().commit();	
//	}
}

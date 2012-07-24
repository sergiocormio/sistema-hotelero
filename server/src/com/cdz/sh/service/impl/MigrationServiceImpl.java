package com.cdz.sh.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cdz.sh.dao.crud.EntityManagerSingleton;
import com.cdz.sh.service.MigrationService;

public class MigrationServiceImpl implements MigrationService {

	private static final String FOLDER_NAME = "foldername";

	@Override
	public void backupDatabase(String absolutePath) {
		EntityManager entityManager = EntityManagerSingleton.getInstance();
		
		entityManager.getTransaction().begin();
		
		Query nativeQuery = EntityManagerSingleton.getInstance().createNativeQuery("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(:foldername)");
		nativeQuery.setParameter(FOLDER_NAME, absolutePath);
		nativeQuery.executeUpdate();
		
		entityManager.getTransaction().commit();
	}

}

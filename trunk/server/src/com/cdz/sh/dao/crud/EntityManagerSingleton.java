package com.cdz.sh.dao.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *  
 * @author fede
 *
 */
public class EntityManagerSingleton {

	private static final String PROVIDER_UNIT_NAME = "dellosky";
	
	private static EntityManager entityManager;
	
	public static synchronized EntityManager getInstance(){
		if(entityManager == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PROVIDER_UNIT_NAME);
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}

}

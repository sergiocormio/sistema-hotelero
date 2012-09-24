package com.cdz.sh.dao.crud;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.Map;

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
	
	
	public static synchronized void shutDown() {
        try {
            DriverManager.getConnection("jdbc:derby:dellosky;shutdown=true").close();
        } 
        /*
         * Derby actually throws an exception when the shutdown is successful. Code 08006 (with SQLCODE 45000) actually means
         * the database was successfully shutdown. Here's the relevant quote from the Derby documentation: 
         * 
         * "A successful shutdown always results in an SQLException to indicate that Derby has shut down and that
         *  there is no other exception."
         *   
         */
        catch(SQLNonTransientConnectionException ex){
        	System.out.println("Error Code: " + ex.getErrorCode());
        	if(ex.getErrorCode() == 45000){
        		System.out.println("Shutdown successful!");
        	}
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }
        if (entityManager != null) {
        	EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
        	entityManager.close();
        	entityManagerFactory.close();
        	entityManager = null;
        }
	}
	
	
	
	/**
	 * Used to restart the database after a restore.
	 * 
	 * Configuration properties programmatically set.
	 * 
	 * 	configuration.put("hibernate.hbm2ddl.auto", "update");  // to keep the data restored
	 * 
	 */
	public static synchronized EntityManager getInstanceRestartingDatabase(){
		if(entityManager == null){
			
			Map<String, Object> configuration = new HashMap<String, Object>();
		    configuration.put("hibernate.connection.url", "jdbc:derby:dellosky;create=true");
		    configuration.put("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		    configuration.put("hibernate.connection.dialect", "org.hibernate.dialect.DerbyDialect");
		    configuration.put("hibernate.connection.username", "");
		    configuration.put("hibernate.connection.password", "");
		    configuration.put("hibernate.hbm2ddl.auto", "update"); 
			
		    configuration.put("hibernate.show_sql", "true");
		    configuration.put("hibernate.format_sql", "true");
	        
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PROVIDER_UNIT_NAME, configuration);
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}

}

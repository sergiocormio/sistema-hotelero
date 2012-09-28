package com.cdz.sh.dao.crud;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *  
 * @author fede
 *
 */
public class EntityManagerFactorySingleton {

	private static final String PROVIDER_UNIT_NAME = "dellosky";
		
	private static EntityManagerFactory entityManagerFactory;
		
	public static synchronized EntityManagerFactory getInstance(){
		if(entityManagerFactory == null){
			entityManagerFactory = Persistence.createEntityManagerFactory(PROVIDER_UNIT_NAME);
		}
		return entityManagerFactory;
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
        if (entityManagerFactory != null) {
        	entityManagerFactory.close();
        	entityManagerFactory = null;
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
	public static synchronized EntityManagerFactory getInstanceRestartingDatabase(){
		if(entityManagerFactory == null){
				
			Map<String, Object> configuration = new HashMap<String, Object>();
		    configuration.put("hibernate.connection.url", "jdbc:derby:dellosky;create=true");
		    configuration.put("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		    configuration.put("hibernate.connection.dialect", "org.hibernate.dialect.DerbyDialect");
		    configuration.put("hibernate.connection.username", "");
		    configuration.put("hibernate.connection.password", "");
		    configuration.put("hibernate.hbm2ddl.auto", "update"); 
			
		    configuration.put("hibernate.show_sql", "true");
		    configuration.put("hibernate.format_sql", "true");
	        
			entityManagerFactory = Persistence.createEntityManagerFactory(PROVIDER_UNIT_NAME, configuration);
			
		}
		return entityManagerFactory;
	}

}

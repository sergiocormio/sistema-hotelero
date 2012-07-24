package com.cdz.sh.service;

/**
 *  
 * @author fede
 *
 */
public interface MigrationService {

	/**
	 * Creates a copy of the DB, in the specified path. If the folder structure does not exist, it is created.
	 * 	
	 * @param absolutePath
	 */
	public void backupDatabase(String absolutePath);
}

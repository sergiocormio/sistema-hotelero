package com.cdz.sh.service;

import java.sql.SQLException;

/**
 *  
 * @author fede
 *
 */
public interface MigrationService {

	/**
	 * Creates a copy of the DB, in the specified path. If the folder structure does not exist, it is created.
	 * 	
	 * @param absolutePath where the backup (zip file) will be created
	 */
	public void backupDatabase(String destZipFile);
	
	
	public void restoreDatabase(String sourceZipFile);
}

package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.service.MigrationService;
import com.cdz.sh.util.ZipUtil;

public class MigrationServiceImpl implements MigrationService {

	
	private static final String CURRENT_PATH = "./";
	private static final String DB_FOLDER_NAME = "dellosky";

	@Override
	public void backupDatabase(String destZipFile) {
		
		ZipUtil.zipFolder(CURRENT_PATH + DB_FOLDER_NAME, destZipFile);
	}
	
	@Override
	public void restoreDatabase(String sourceZipFile) {
		
		EntityManagerFactorySingleton.shutDown();
		
		ZipUtil.unzipFolder(sourceZipFile, CURRENT_PATH);
		
		EntityManagerFactorySingleton.getInstanceRestartingDatabase();
	}
	
	
//	private void deleteFolder(String path) {
//	    File folder = new File(path);
//	    
//		File[] files = folder.listFiles();
//	    if(files!=null) { //some JVMs return null for empty dirs
//	        for(File f: files) {
//	            if(f.isDirectory()) {
//	                deleteFolder(f.getAbsolutePath());
//	            } else {
//	                f.delete();
//	            }
//	        }
//	    }
//	    folder.delete();
//	}


}

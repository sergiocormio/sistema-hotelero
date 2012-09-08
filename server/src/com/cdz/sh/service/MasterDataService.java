package com.cdz.sh.service;

/**
 * Responsible for creating master data
 * 
 * @author fede
 *
 */
public interface MasterDataService {

	
	/**
	 * create the master data. It also checks if the records are currently created so as not to have a constraint exception 
	 */
	public void createMasterData();
	
	
}

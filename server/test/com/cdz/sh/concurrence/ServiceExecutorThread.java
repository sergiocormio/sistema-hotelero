package com.cdz.sh.concurrence;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.service.CrudService;

import static org.junit.Assert.assertTrue;

public class ServiceExecutorThread implements Runnable {

	private CrudService service;
	
	public ServiceExecutorThread(CrudService service) {
		this.service = service;
	}
	
	
	@Override
	public void run() {
		
		try{
			this.service.retrieveAll();
		}
		catch(DaoException daoException){
			daoException.printStackTrace();
			assertTrue(false);
		}

	}

}

package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.impl.CleaningDaoImpl;
import com.cdz.sh.model.Cleaning;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CleaningService;

/**
 * Implementation of CleaningService facade
 * 
 * @author fede
 *
 */
public class CleaningServiceImpl extends AbstractCrudService<Cleaning, Long> implements CleaningService {

	public CleaningServiceImpl() {
		this.crudDao = new CleaningDaoImpl();
	}

	@Override
	public List<Cleaning> retrieveRoomsToClean(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String exportData(List<Cleaning> cleanings) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}

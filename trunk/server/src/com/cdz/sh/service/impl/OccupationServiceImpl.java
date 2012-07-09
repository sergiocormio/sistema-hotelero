package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.OccupationService;

/**
 * Implementation of OccupationService facade
 * 
 * @author fede
 *
 */
public class OccupationServiceImpl extends AbstractCrudService<Occupation, OccupationPK> implements OccupationService {

	
	private OccupationDao occupationDao; 
	
	

	@Override
	protected CrudDao<Occupation, OccupationPK> createDao() {
		OccupationDaoImpl occupationDaoImpl = new OccupationDaoImpl();
		
		// to be able to access specific occupationDao methods
		this.occupationDao = occupationDaoImpl;
		
		//to have CRUD operations
		return occupationDaoImpl;
	}
	
	

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) {
		
		return this.occupationDao.retrieveOccupations(dateFrom, dateTo);
	}

	@Override
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int peopleQty, int variance) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}

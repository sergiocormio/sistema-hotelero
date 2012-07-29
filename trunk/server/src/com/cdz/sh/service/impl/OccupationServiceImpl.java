package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
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
				
		// to be able to access specific occupationDao methods
		this.occupationDao = new OccupationDaoImpl();
				
		//to have CRUD operations
		return this.occupationDao;
	}
	
	

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) {
		List<Occupation> occupations = null;
	try {
			occupations = this.occupationDao.retrieveOccupations(dateFrom, dateTo);
		} catch (DaoException e) {
			// TODO ver que hacer con la excepcion
			e.printStackTrace();
		}
		return occupations;
	}

	@Override
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int peopleQty, int variance) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}

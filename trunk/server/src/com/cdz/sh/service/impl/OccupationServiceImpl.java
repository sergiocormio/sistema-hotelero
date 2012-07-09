package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

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

	public OccupationServiceImpl() {
		this.crudDao = new OccupationDaoImpl();
	}

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alternative> checkAvailability(Date dateFrom, Date dateTo, int peopleQty, int variance) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}

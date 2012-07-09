package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class OccupationDaoImpl extends AbstractCrudDao<Occupation, OccupationPK> implements OccupationDao {

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	


	
}

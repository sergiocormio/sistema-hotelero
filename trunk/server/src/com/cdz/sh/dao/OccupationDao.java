package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface OccupationDao extends CrudDao<Occupation, OccupationPK> {

	
	List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException;


}

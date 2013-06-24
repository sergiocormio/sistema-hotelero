package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Cleaning;
import com.cdz.sh.model.CleaningPK;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface CleaningDao extends CrudDao<Cleaning, CleaningPK> {

	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException;

}

package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Cleaning;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface CleaningDao extends CrudDao<Cleaning, Long> {


	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException;

}

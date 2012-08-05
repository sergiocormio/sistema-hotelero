package com.cdz.sh.dao;

import java.util.Date;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RatePK;
import com.cdz.sh.model.RoomType;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface RateDao extends CrudDao<Rate, RatePK> {

	public Rate retrieveRate(RoomType roomType, Date date) throws DaoException;

	

}

package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Promotion;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface PromotionDao extends CrudDao<Promotion, Long> {

	

	public List<Promotion> retrieveContainedPromotions(Date dateFrom, Date dateTo) throws DaoException;
}

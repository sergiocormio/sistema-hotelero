package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.RateDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RoomType;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class RateDaoImpl extends AbstractCrudDao<Rate, Long> implements RateDao {

	@Override
	public Rate retrieveRate(RoomType roomType, Date date) throws DaoException {
		try {	
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT r FROM Rate r WHERE r.roomType = :roomType and r.season.dateFrom <= :date and r.season.dateTo >= :date";
			
			TypedQuery<Rate> query = entityManager.createQuery(strQuery, Rate.class);
			
			query = query.setParameter("roomType", roomType);
			query = query.setParameter("date", date);
			
			List<Rate> rates = query.getResultList();
			entityManager.getTransaction().commit();
			if(rates != null && rates.size() == 1){
				return rates.get(0);
			}
			return null;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	
	
}

package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.SeasonDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Season;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class SeasonDaoImpl extends AbstractCrudDao<Season, Long> implements SeasonDao {

	
	@Override
	public Season createRecord(Season season) throws DaoException {
		
		// check if we can create the season
		checkOverlaping(season);
		
		return super.createRecord(season);
	}
	
	

	@Override
	public void updateRecord(Season season) throws DaoException {
		
		// check if we can update the season
		checkOverlaping(season);
				
		super.updateRecord(season);
	}



	private void checkOverlaping(Season season) throws DaoException {
	
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT s FROM Season s WHERE (s.dateFrom <= :dateFrom and s.dateTo >= :dateFrom) OR" +
					" (s.dateFrom <= :dateTo and s.dateTo >= :dateTo) OR" +
					" (s.dateFrom >= :dateFrom and s.dateTo <= :dateTo)";
			
			TypedQuery<Season> query = entityManager.createQuery( strQuery, Season.class);
			
			query = query.setParameter("dateFrom", season.getDateFrom());
			query = query.setParameter("dateTo", season.getDateTo());
			
			List<Season> seasons = query.getResultList();
			entityManager.getTransaction().commit();
			
			if(seasons != null && !seasons.isEmpty()){
				throw new DaoException("The season can not be created or updated because the range of dates could overlap an axisting season.");
			}
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	
}

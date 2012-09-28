package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.RegionDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Region;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class RegionDaoImpl extends AbstractCrudDao<Region, Long> implements RegionDao {

	@Override
	public synchronized List<Region> retrieveRegions(Country country) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT r FROM Region r WHERE r.country = :country";
			
			TypedQuery<Region> query = entityManager.createQuery( strQuery, Region.class);
			
			query = query.setParameter("country", country);
						
			List<Region> regions = query.getResultList();
			entityManager.getTransaction().commit();
			return regions;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}



	
}

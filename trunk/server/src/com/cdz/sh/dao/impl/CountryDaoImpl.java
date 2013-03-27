package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.CountryDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific Country methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class CountryDaoImpl extends AbstractCrudDao<Country, Long> implements CountryDao {

	
	public List<Country> retrieveCountriesWithoutRegions() throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Country c WHERE c not in (SELECT c2 from Region r, Country c2 WHERE r.country = c2)";
			
			TypedQuery<Country> query = entityManager.createQuery(strQuery, Country.class);
			
			List<Country> countriesWithoutRegions = query.getResultList();
			entityManager.getTransaction().commit();
			return countriesWithoutRegions;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}

		
	}
}

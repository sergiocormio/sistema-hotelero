package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.CleaningDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Cleaning;
import com.cdz.sh.model.CleaningPK;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class CleaningDaoImpl extends AbstractCrudDao<Cleaning, CleaningPK> implements CleaningDao {

	
	@Override
	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Cleaning c WHERE c.id.date = :date";
			
			TypedQuery<Cleaning> query = entityManager.createQuery( strQuery, Cleaning.class);
			
			query = query.setParameter("date", date);
			
			List<Cleaning> cleanings = query.getResultList();
			entityManager.getTransaction().commit();
			return cleanings;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}

	
}

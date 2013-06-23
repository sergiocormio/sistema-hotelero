package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.ConsumptionDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Room;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class ConsumptionDaoImpl extends AbstractCrudDao<Consumption, Long> implements ConsumptionDao {

	
	
	@Override
	public List<Consumption> retrieveConsumptions(Date dateFrom, Date dateTo, Room room) throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Consumption c WHERE c.date >= :dateFrom and c.date <= :dateTo and c.room = :room";
			
			TypedQuery<Consumption> query = entityManager.createQuery(strQuery, Consumption.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			query = query.setParameter("room", room);
			
			List<Consumption> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
		
		
	}

	
	@Override
	public List<Consumption> retrieveConsumptions(Occupation occupation) throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Consumption c WHERE c.date = :date and c.room = :room";
			
			TypedQuery<Consumption> query = entityManager.createQuery(strQuery, Consumption.class);
			
			query = query.setParameter("date", occupation.getId().getDate());
			query = query.setParameter("room", occupation.getId().getRoom());
			
			List<Consumption> consumptions = query.getResultList();
			entityManager.getTransaction().commit();
			return consumptions;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
		
		
	}
}

package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.PromotionDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Promotion;
import com.cdz.sh.model.Season;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class PromotionDaoImpl extends AbstractCrudDao<Promotion, Long> implements PromotionDao {


	public List<Promotion> retrieveContainedPromotions(Date dateFrom, Date dateTo) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
//			String strQuery = "SELECT p FROM Promotion p WHERE ((p.dateFrom <= :dateFrom and p.dateTo >= :dateFrom) OR" +
//					" (p.dateFrom <= :dateTo and p.dateTo >= :dateTo) OR" +
//					" (p.dateFrom >= :dateFrom and p.dateTo <= :dateTo))";

			String strQuery = "SELECT p FROM Promotion p WHERE p.dateFrom >= :dateFrom and p.dateTo <= :dateTo";
			
			TypedQuery<Promotion> query = entityManager.createQuery( strQuery, Promotion.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			
			List<Promotion> promotions = query.getResultList();
			entityManager.getTransaction().commit();
			
			return promotions;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}

	
}

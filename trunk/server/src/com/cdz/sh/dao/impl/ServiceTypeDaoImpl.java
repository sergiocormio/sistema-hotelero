package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.ServiceTypeDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ServiceType;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class ServiceTypeDaoImpl extends AbstractCrudDao<ServiceType, Long> implements ServiceTypeDao {

	@Override
	public synchronized List<ServiceType> retrieveAdditionalServices() throws DaoException {
		
		Boolean includedInBudget = Boolean.TRUE;
		Boolean includedInBasePrice = Boolean.FALSE;
		
		return doQuery(includedInBudget, includedInBasePrice);
	}
	
	@Override
	public synchronized List<ServiceType> retrieveServicesIncludedInBasePrice() throws DaoException {
		
		Boolean includedInBudget = Boolean.TRUE;
		Boolean includedInBasePrice = Boolean.TRUE;
		
		return doQuery(includedInBudget, includedInBasePrice);
		
	}
	
	private synchronized List<ServiceType> doQuery(boolean includedInBudget, boolean includedInBasePrice) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT st FROM ServiceType st WHERE st.includedInBudget = :includedInBudget";
			strQuery = strQuery.concat(" and st.includedInBasePrice = :includedInBasePrice");
			TypedQuery<ServiceType> query = entityManager.createQuery( strQuery, ServiceType.class);
			
			query = query.setParameter("includedInBudget", includedInBudget);
			query = query.setParameter("includedInBasePrice", includedInBasePrice);
			
			List<ServiceType> serviceTypes = query.getResultList();
			entityManager.getTransaction().commit();
			return serviceTypes;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}

	
	public List<ServiceType> getTransferServiceTypes() throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT st FROM ServiceType st WHERE st.transferType is not null";
			
			TypedQuery<ServiceType> query = entityManager.createQuery( strQuery, ServiceType.class);
			
			List<ServiceType> serviceTypes = query.getResultList();
			entityManager.getTransaction().commit();
			return serviceTypes;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}
	
	
//	@Override
//	public synchronized void deleteRecord(ServiceType serviceType) throws DaoException {
//		if(serviceType.getId() <= 9){
//			throw new DaoException("This service can not be removed (system-defined)");
//		}
//		super.deleteRecord(serviceType);
//	}

	
	
	
}

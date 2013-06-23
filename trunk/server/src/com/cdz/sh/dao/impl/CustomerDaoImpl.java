package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.AddressDao;
import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Address;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Region;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class CustomerDaoImpl extends AbstractCrudDao<Customer, Long> implements CustomerDao {

	private AddressDao addressDao;
	
	public CustomerDaoImpl(){
		this.addressDao = new AddressDaoImpl();
	}
	
	
	@Override
	public List<Customer> retrieveCustomers(List<Region> regions) throws DaoException {

		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Customer c WHERE c.region in (:regions)";
			
			TypedQuery<Customer> query = entityManager.createQuery(strQuery, Customer.class);
			
			query = query.setParameter("regions", regions);
			
			List<Customer> customers = query.getResultList();
			entityManager.getTransaction().commit();
			return customers;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}

	}

	@Override
	public synchronized Customer createRecord(Customer e) throws DaoException {
		if(e.getAddress() != null){
			this.addressDao.createRecord(e.getAddress());
		}
		return super.createRecord(e);
	}

	@Override
	public synchronized void updateRecord(Customer e) throws DaoException {
		if(e.getAddress() != null){
			Address address = e.getAddress();
			if(address.getId() != null){
				this.addressDao.updateRecord(address);
			}
			else{
				address = this.addressDao.createRecord(address);
				e.setAddress(address);
			}
			
		}
		super.updateRecord(e);
	}

	@Override
	public synchronized void deleteRecord(Customer e) throws DaoException {
		if(e.getAddress() != null){
			Address address = e.getAddress();
			if(address.getId() != null){
				e.setAddress(null);
				updateRecord(e);
				this.addressDao.deleteRecord(address);
			}
			else{
				e.setAddress(null);
			}
		}
		super.deleteRecord(e);
	}

	
	
	
}

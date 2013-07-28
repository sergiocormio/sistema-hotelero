package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.AddressDao;
import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.model.Address;
import com.cdz.sh.model.Country;
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
	public List<Customer> retrieveAll() throws DaoException {

		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Customer c ORDER BY email ASC"; 
			
			TypedQuery<Customer> query = entityManager.createQuery(strQuery, Customer.class);
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
	public synchronized List<Customer> retrieveCustomers(String email, String firstName, String lastName, Country country) throws InvalidParameterException, DaoException {

		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String jpql = createJpql(email, firstName, lastName, country);
		try{
			entityManager.getTransaction().begin();
			TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
			
			query = setParameters(query, email, firstName, lastName, country);
			
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


	private String createJpql(String email, String firstName, String lastName,Country country) throws InvalidParameterException {
		
		int parametersQty = 0;
		
		String jpql = "select c from Customer c where ";
		if(email != null){
			if(parametersQty > 0){
				jpql = jpql.concat(" and ");
			}
			parametersQty++;
			jpql = jpql.concat("c.email = :email");
		}
		if(firstName != null){
			if(parametersQty > 0){
				jpql = jpql.concat(" and ");
			}
			parametersQty++;
			jpql = jpql.concat("c.firstName = :firstName");
		}
		if(lastName != null){
			if(parametersQty > 0){
				jpql = jpql.concat(" and ");
			}
			parametersQty++;
			jpql = jpql.concat("c.lastName = :lastName");
		}
		if(country != null){
			if(parametersQty > 0){
				jpql = jpql.concat(" and ");
			}
			parametersQty++;
			// as we are not using left join, this will act as an inner join, hence, not retrieving 
			// customers with null addresses...
			jpql = jpql.concat("c.address.region.country = :country"); 
		}
		if(parametersQty == 0){
			throw new InvalidParameterException(ExceptionErrorCodes.NO_PARAMETERS_SPECIFIED, "Al least one parameter should be specified");
		}
		return jpql;
	}

	
	private TypedQuery<Customer> setParameters(TypedQuery<Customer> query, String email,	String firstName, 
														String lastName, Country country) {
		if(email != null){
			query = query.setParameter("email", email);
		}
		if(firstName != null){
			query = query.setParameter("firstName", firstName);
		}
		if(lastName != null){
			query = query.setParameter("lastName", lastName);
		}
		if(country != null){
			query = query.setParameter("country", country);
		}
		
		return query;
	}
	
	
	
	@Override
	public List<Customer> retrieveCustomers(List<Region> regions, boolean includeCustomersWithoutRegion) throws DaoException {

		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT c FROM Customer c "; 
			
			if(regions != null && !regions.isEmpty()){
				/**
				 * LEFT JOIN to keep all customers and eventually be able to retrieve customers with address = null.
				 * If we do not use a LEFT JOIN, the WHERE will act as an INNER JOIN and filter customers with address = null
				 * before-hand. 
				 */
				strQuery += "LEFT JOIN c.address.region WHERE c.address.region in (:regions) ";
			}
						
			if(includeCustomersWithoutRegion){
				//if we have filtered by region
				if(regions != null && !regions.isEmpty()){
					strQuery += " OR ";
				}
				else{
					strQuery += "WHERE ";
				}
				
				strQuery += "(c.address IS NULL)"; //if the address is null, the region is null
			}
			
			TypedQuery<Customer> query = entityManager.createQuery(strQuery, Customer.class);
			
			if(regions != null && !regions.isEmpty()){
				query = query.setParameter("regions", regions);
			}
			
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

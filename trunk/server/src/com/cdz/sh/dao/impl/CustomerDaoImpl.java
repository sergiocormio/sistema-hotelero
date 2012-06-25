package com.cdz.sh.dao.impl;

import java.util.Collection;

import javax.persistence.TypedQuery;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.model.Customer;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class CustomerDaoImpl extends AbstractCrudDao<Customer, Long> implements CustomerDao {

	@Override
	public Collection<Customer> retrieveAll() {
	
		entityManager.getTransaction().begin();
		
		TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
		Collection<Customer> customers = query.getResultList();
        
		entityManager.getTransaction().commit();
	    
        return customers;
	}


	
}

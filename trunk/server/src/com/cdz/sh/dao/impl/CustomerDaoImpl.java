package com.cdz.sh.dao.impl;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class CustomerDaoImpl extends AbstractCrudDao<Customer, CustomerPK> implements CustomerDao {

	/**
	 * TODO implement specific queries
	 */


	
}

package com.cdz.sh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;

/**
 * Declares specific functionality for countries access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface CountryDao extends CrudDao<Country, Long> {

	
	public List<Country> retrieveCountriesWithoutRegions() throws DaoException;

	
	
}

package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.ReservationFormDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.StateReservationForm;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class ReservationFormDaoImpl extends AbstractCrudDao<ReservationForm, Long> implements ReservationFormDao {

	@Override
	public List<ReservationForm> retrieveReservationForms(Date dateFrom, Date dateTo, Customer customer, StateReservationForm state) throws InvalidParameterException, DaoException {

		String jpql = createJpql(dateFrom, dateTo, customer, state);
		try{
			entityManager.getTransaction().begin();
			TypedQuery<ReservationForm> query = entityManager.createQuery(jpql, ReservationForm.class);
			
			query = setParameters(query, dateFrom, dateTo, customer, state);
			
			List<ReservationForm> reservationForms = query.getResultList();
			entityManager.getTransaction().commit();
			
			return reservationForms;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}

	}


	private String createJpql(Date dateFrom, Date dateTo, Customer customer,StateReservationForm state) throws InvalidParameterException {
		
		boolean oneParameterNotNull = false;
		
		String jpql = "select rf from ReservationForm rf where ";
		if(dateFrom != null){
			oneParameterNotNull = true;
			jpql = jpql.concat("rf.dateFrom >= :dateFrom");
		}
		if(dateTo != null){
			oneParameterNotNull = true;
			jpql = jpql.concat("rf.dateTo <= :dateTo");
		}
		if(customer != null){
			oneParameterNotNull = true;
			jpql = jpql.concat("rf.customer = :customer");
		}
		if(state != null){
			oneParameterNotNull = true;
			jpql = jpql.concat("rf.state = :state");
		}
		if(!oneParameterNotNull){
			throw new InvalidParameterException("Al least one parameter should be specified");
		}
		return jpql;
	}

	
	private TypedQuery<ReservationForm> setParameters(TypedQuery<ReservationForm> query, Date dateFrom,	Date dateTo, 
														Customer customer, StateReservationForm state) {
		if(dateFrom != null){
			query = query.setParameter("dateFrom", dateFrom);
		}
		if(dateTo != null){
			query = query.setParameter("dateTo", dateTo);
		}
		if(customer != null){
			query = query.setParameter("customer", customer);
		}
		if(state != null){
			query = query.setParameter("state", state);
		}
		
		return query;
	}
	
}

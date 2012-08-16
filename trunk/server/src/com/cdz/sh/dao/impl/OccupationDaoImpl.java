package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class OccupationDaoImpl extends AbstractCrudDao<Occupation, OccupationPK> implements OccupationDao {

	@Override
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException {
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date >= :dateFrom and oc.id.date <= :dateTo";
			
			TypedQuery<Occupation> query = entityManager.createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	@Override
	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException {
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.reservationForm = :reservationForm";
			
			TypedQuery<Occupation> query = entityManager.createQuery(strQuery, Occupation.class);
			
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	


	
}

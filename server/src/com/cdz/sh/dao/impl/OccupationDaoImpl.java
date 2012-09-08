package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.constants.MasterDataConstants;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerSingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.StateReservationForm;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class OccupationDaoImpl extends AbstractCrudDao<Occupation, OccupationPK> implements OccupationDao {

	@Override
	public synchronized List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException {
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date >= :dateFrom and oc.id.date <= :dateTo";
			
			TypedQuery<Occupation> query = EntityManagerSingleton.getInstance().createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			
			List<Occupation> occupations = query.getResultList();
			EntityManagerSingleton.getInstance().getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}
	
	
	@Override
	public synchronized List<Occupation> retrieveConfirmedOccupations(Date dateFrom, Date dateTo, int adultsQuantity, int childrenQuantity) throws DaoException {
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date >= :dateFrom and oc.id.date <= :dateTo";
			strQuery = strQuery.concat(" and oc.id.room.peopleQuantity >= :peopleQuantity");
			strQuery = strQuery.concat(" and oc.id.reservationForm.state.id = :stateConfirmedId");
			
			TypedQuery<Occupation> query = EntityManagerSingleton.getInstance().createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			query = query.setParameter("peopleQuantity", adultsQuantity + childrenQuantity);
			query = query.setParameter("stateConfirmedId", StateReservationForm.confirmada);
			
			
			List<Occupation> occupations = query.getResultList();
			EntityManagerSingleton.getInstance().getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}
	
	

	@Override
	public synchronized List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException {
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.reservationForm = :reservationForm";
			
			TypedQuery<Occupation> query = EntityManagerSingleton.getInstance().createQuery(strQuery, Occupation.class);
			
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Occupation> occupations = query.getResultList();
			EntityManagerSingleton.getInstance().getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}


	@Override
	public synchronized List<Occupation> retrieveOverlapedOccupations(Occupation occupation, ReservationForm reservationForm) throws DaoException {
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date = :date";
			strQuery = strQuery.concat(" and oc.id.room = :room");
			strQuery = strQuery.concat(" and oc.id.reservationForm != :reservationForm");
			
			TypedQuery<Occupation> query = EntityManagerSingleton.getInstance().createQuery(strQuery, Occupation.class);
			
			query = query.setParameter("date", occupation.getId().getDate());
			query = query.setParameter("room", occupation.getId().getRoom());
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Occupation> occupations = query.getResultList();
			EntityManagerSingleton.getInstance().getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	


	
}

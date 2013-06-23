package com.cdz.sh.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.model.request.CheckAvailabilityRequest;

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
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		finally{
			entityManager.close();
		}
	}
	
	
	@Override
	public List<Occupation> retrieveConfirmedOccupations(CheckAvailabilityRequest request) throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date >= :dateFrom and oc.id.date <= :dateTo";
			strQuery = strQuery.concat(" and oc.id.room.peopleQuantity >= :peopleQuantity");
			strQuery = strQuery.concat(" and oc.id.room.withMaritalBed = :withMaritalBed");			
			strQuery = strQuery.concat(" and oc.id.reservationForm.state = :stateConfirmedId");
			
			TypedQuery<Occupation> query = entityManager.createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("dateFrom", request.getDateFrom());
			query = query.setParameter("dateTo", request.getDateTo());
			
			int peopleQuantity = request.getAdultsQty() + request.getChildrenQty();
			query = query.setParameter("peopleQuantity", peopleQuantity);
			
			query = query.setParameter("withMaritalBed", request.isWithMaritalBed());
			query = query.setParameter("stateConfirmedId", StateReservationForm.CONFIRMED);
					
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}
	
	

	@Override
	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.reservationForm = :reservationForm";
			
			TypedQuery<Occupation> query = entityManager.createQuery(strQuery, Occupation.class);
			
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}


	@Override
	public synchronized List<Occupation> retrieveOverlapedOccupations(Occupation occupation, ReservationForm reservationForm) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date = :date";
			strQuery = strQuery.concat(" and oc.id.room = :room");
			strQuery = strQuery.concat(" and oc.id.reservationForm != :reservationForm");
			
			TypedQuery<Occupation> query = entityManager.createQuery(strQuery, Occupation.class);
			
			query = query.setParameter("date", occupation.getId().getDate());
			query = query.setParameter("room", occupation.getId().getRoom());
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}


	@Override
	public synchronized List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo, Room room) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date >= :dateFrom and oc.id.date <= :dateTo";
			strQuery = strQuery.concat(" and oc.id.room = :room");
			
			TypedQuery<Occupation> query = entityManager.createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("dateFrom", dateFrom);
			query = query.setParameter("dateTo", dateTo);
			query = query.setParameter("room", room);
			
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}

	

	@Override
	public List<Occupation> retrieveConfirmedOccupations(Date date) throws DaoException {
		
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT oc FROM Occupation oc WHERE oc.id.date = :date and oc.id.reservationForm.state = :stateConfirmedId";
			
			TypedQuery<Occupation> query = entityManager.createQuery( strQuery, Occupation.class);
			
			query = query.setParameter("date", date);
			query = query.setParameter("stateConfirmedId", StateReservationForm.CONFIRMED);
					
			List<Occupation> occupations = query.getResultList();
			entityManager.getTransaction().commit();
			return occupations;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}

	
	
}

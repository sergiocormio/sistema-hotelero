package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerFactorySingleton;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;

/**
 * The idea of each concrete class (like this one) is to ONLY add specific customer methods. CRUD operations are implemented 
 * on the abstract class using generics
 *  
 * @author fede
 *
 */
public class RoomDaoImpl extends AbstractCrudDao<Room, Long> implements RoomDao {

	@Override
	public List<Room> retrieveRoomsByCapacity(int adultQty, int childrenQty, boolean withMaritalBed) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT r FROM Room r WHERE ";
			strQuery = strQuery.concat(" r.peopleQuantity >= :peopleQuantity");
			strQuery = strQuery.concat(" and r.withMaritalBed = :withMaritalBed");
			
			TypedQuery<Room> query = entityManager.createQuery( strQuery, Room.class);
			
			int peopleQty = adultQty + childrenQty;
			
			query = query.setParameter("peopleQuantity", peopleQty);
			query = query.setParameter("withMaritalBed", withMaritalBed);
						
			List<Room> rooms = query.getResultList();
			entityManager.getTransaction().commit();
			return rooms;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}


	@Override
	public List<Room> retrieveDistinctRooms(ReservationForm reservationForm) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			String strQuery = "SELECT DISTINCT oc.id.room FROM Occupation oc WHERE oc.id.reservationForm = :reservationForm";
			
			TypedQuery<Room> query = entityManager.createQuery(strQuery, Room.class);
			
			query = query.setParameter("reservationForm", reservationForm);
						
			List<Room> rooms = query.getResultList();
			entityManager.getTransaction().commit();
			return rooms;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			entityManager.close();
		}
	}
}

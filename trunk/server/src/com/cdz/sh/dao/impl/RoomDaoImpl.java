package com.cdz.sh.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.AbstractCrudDao;
import com.cdz.sh.dao.crud.EntityManagerSingleton;
import com.cdz.sh.dao.exception.DaoException;
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
	public synchronized List<Room> retrieveRoomsByCapacity(int capacity) throws DaoException {
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			
			String strQuery = "SELECT r FROM Room r WHERE r.totalPeopleQuantity >= :totalPeopleQuantity";
			
			TypedQuery<Room> query = EntityManagerSingleton.getInstance().createQuery( strQuery, Room.class);
			
			query = query.setParameter("totalPeopleQuantity", capacity);
						
			List<Room> rooms = query.getResultList();
			EntityManagerSingleton.getInstance().getTransaction().commit();
			return rooms;
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
	}

	
}

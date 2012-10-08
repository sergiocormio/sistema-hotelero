package com.cdz.sh.dao;

import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Room;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface RoomDao extends CrudDao<Room, Long> {

	public List<Room> retrieveRoomsByCapacity(int adultQty, int childrenQty, boolean withMaritalBed) throws DaoException;

}

package com.cdz.sh.service.impl;

import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.RoomService;

/**
 * Implementation of RoomService facade
 * 
 * @author fede
 *
 */
public class RoomServiceImpl extends AbstractCrudService<Room, Long> implements RoomService {

	public RoomServiceImpl() {
		this.crudDao = new RoomDaoImpl();
	}

	
	
	

}

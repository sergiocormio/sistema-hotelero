package com.cdz.sh.service.core.roombuilder;

import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.RoomTypeDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.RoomType;


/**
 * 1 roomType
 * 3 rooms for that roomType
 * 
 * @author fede
 *
 */
public class SameRoomTypeBuilder {

	private RoomTypeDao roomTypeDao;
	private RoomDao roomDao;
	
	
	public SameRoomTypeBuilder(){
		
		this.roomTypeDao = new RoomTypeDaoImpl();
		this.roomDao = new RoomDaoImpl();
	}
	
	public void buildRooms(){
		
		try {
			// Room Types
			RoomType roomType = createRoomType();
			
			// Rooms
			createRoom(roomType);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	private void createRoom(RoomType roomType) throws DaoException {
		Room roomOfTypeCostao1 = this.roomDao.getRecordById(1L);
		if (roomOfTypeCostao1 == null){
			roomOfTypeCostao1 = new Room();
			roomOfTypeCostao1.setRoomType(roomType);
			roomOfTypeCostao1.setNumber(1);
			roomOfTypeCostao1.setAdultsQuantity(2);
			roomOfTypeCostao1.setChildrenQuantity(2);
			this.roomDao.createRecord(roomOfTypeCostao1);
		}
		
		Room roomOfTypeCostao2 = this.roomDao.getRecordById(2L);
		if (roomOfTypeCostao2 == null){
			roomOfTypeCostao2 = new Room();
			roomOfTypeCostao2.setRoomType(roomType);
			roomOfTypeCostao2.setNumber(2);
			roomOfTypeCostao2.setAdultsQuantity(2);
			roomOfTypeCostao2.setChildrenQuantity(2);
			this.roomDao.createRecord(roomOfTypeCostao2);
		}
		
		Room roomOfTypeCostao3 = this.roomDao.getRecordById(3L);
		if (roomOfTypeCostao3 == null){
			roomOfTypeCostao3 = new Room();
			roomOfTypeCostao3.setRoomType(roomType);
			roomOfTypeCostao3.setNumber(3);
			roomOfTypeCostao3.setAdultsQuantity(2);
			roomOfTypeCostao3.setChildrenQuantity(2);
			this.roomDao.createRecord(roomOfTypeCostao3);
		}
		
	}

	private RoomType createRoomType() throws DaoException {
		RoomType roomTypeCostao = this.roomTypeDao.getRecordById(1L);
		if (roomTypeCostao == null){
			roomTypeCostao = new RoomType();
			roomTypeCostao.setName("Costão");
			roomTypeCostao.setDescription(" Térreo, ideal para 4 pessoas ou família.﻿"
										 + "﻿﻿﻿ Sala / cozinha conjugada com sofa cama, quarto com cama casal, patio privativo com churrasqueira."
									 	 + " Oferece café da manhã e roupas de cama. ");
			this.roomTypeDao.createRecord(roomTypeCostao);
		}
		return roomTypeCostao;
	}
	
	
}

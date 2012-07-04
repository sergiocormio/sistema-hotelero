package com.cdz.sh.service.impl;

import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.model.RoomType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.RoomTypeService;

/**
 * Implementation of RoomTypeService facade
 * 
 * @author fede
 *
 */
public class RoomTypeServiceImpl extends AbstractCrudService<RoomType, Long> implements RoomTypeService {

	public RoomTypeServiceImpl() {
		this.crudDao = new RoomTypeDaoImpl();
	}

	
	
	

}

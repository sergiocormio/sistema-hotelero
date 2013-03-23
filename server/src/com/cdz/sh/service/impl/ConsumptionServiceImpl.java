package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.ConsumptionDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ConsumptionDaoImpl;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ConsumptionService;

/**
 * Implementation of ConsumptionService facade
 * 
 * @author fede
 *
 */
public class ConsumptionServiceImpl extends AbstractCrudService<Consumption, Long> implements ConsumptionService {

	private ConsumptionDao consumptionDao;
	
	@Override
	protected CrudDao<Consumption, Long> createDao() {
		this.consumptionDao = new ConsumptionDaoImpl();
		return consumptionDao;
	}

	@Override
	public List<Consumption> retrieveConsumptions(Date dateFrom, Date dateTo, Room room) throws DaoException {
		
		return this.consumptionDao.retrieveConsumptions(dateFrom, dateTo, room);
	}
	
	
	

}

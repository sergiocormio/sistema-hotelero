package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.StateReservationFormDaoImpl;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.StateReservationFormService;

/**
 * Implementation of StateReservationFormService facade
 * 
 * @author fede
 *
 */
public class StateReservationFormServiceImpl extends AbstractCrudService<StateReservationForm, Long> implements StateReservationFormService {

	@Override
	protected CrudDao<StateReservationForm, Long> createDao() {
		return new StateReservationFormDaoImpl();
	}


	
	

}

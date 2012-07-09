package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ReservationFormService;

/**
 * Implementation of ReservationFormService facade
 * 
 * @author fede
 *
 */
public class ReservationFormServiceImpl extends AbstractCrudService<ReservationForm, Long> implements ReservationFormService {

	public ReservationFormServiceImpl() {
		this.crudDao = new ReservationFormDaoImpl();
	}

	@Override
	public List<ReservationForm> retrieveReservationForms(Date dateFrom,
			Date dateTo, int reservationFormNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean book(Alternative chosenAlternative) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String exportData(ReservationForm reservationForm) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}

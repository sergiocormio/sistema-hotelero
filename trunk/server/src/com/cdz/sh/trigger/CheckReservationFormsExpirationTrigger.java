package com.cdz.sh.trigger;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.ReservationFormDao;
import com.cdz.sh.dao.ReservationFormExpirationDaysDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormExpirationDaysDaoImpl;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.ReservationFormExpirationDays;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.util.DateUtil;

public class CheckReservationFormsExpirationTrigger implements Trigger {

	private ReservationFormDao reservationFormDao;
	private ReservationFormExpirationDaysDao expirationDaysDao;
	
	public CheckReservationFormsExpirationTrigger(){
		
		this.expirationDaysDao = new ReservationFormExpirationDaysDaoImpl();
		this.reservationFormDao = new ReservationFormDaoImpl();
	}
	
	@Override
	public void executeAction() throws DaoException {
		
		ReservationFormExpirationDays expiration = this.expirationDaysDao.getRecordById(1l);
		List<ReservationForm> reservationForms;
		try {
			reservationForms = this.reservationFormDao.retrieveReservationForms(null, null, null, StateReservationForm.PRE_BOOKING);
			
			for (ReservationForm reservationForm : reservationForms) {
				int daysDifference = DateUtil.getDaysDifference(reservationForm.getCreationDate(), new Date());
				
				if(daysDifference > expiration.getDaysToExpire()){
					reservationForm.setState(StateReservationForm.EXPIRED);
					this.reservationFormDao.updateRecord(reservationForm);
				}
			}
			
		} catch (InvalidParameterException e) {
			// this hould never happen bacause I'm the one that I'm calling the retrieveReservationForms method
			e.printStackTrace();
		}

		
	}

}

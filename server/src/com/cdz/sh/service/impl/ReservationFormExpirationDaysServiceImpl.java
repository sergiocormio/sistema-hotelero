package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ReservationFormExpirationDaysDaoImpl;
import com.cdz.sh.model.ReservationFormExpirationDays;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ReservationFormExpirationDaysService;

public class ReservationFormExpirationDaysServiceImpl extends AbstractCrudService<ReservationFormExpirationDays, Long> implements ReservationFormExpirationDaysService {

	@Override
	protected CrudDao<ReservationFormExpirationDays, Long> createDao() {
		return new ReservationFormExpirationDaysDaoImpl();
	}

}

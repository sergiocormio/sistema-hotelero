package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Transfer;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface TransferDao extends CrudDao<Transfer, Long> {

	
	public List<Transfer> retrieveTransfers(Date dateFrom, Date dateTo) throws DaoException;

	public List<Transfer> retrieveTransfers(ReservationForm reservationForm) throws DaoException;

}

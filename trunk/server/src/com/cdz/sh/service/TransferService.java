package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Transfer;
import com.cdz.sh.service.exception.InvalidOperationException;

/**
 * Facade for Transfer entity
 * 
 * @author fede
 *
 */
public interface TransferService extends CrudService<Transfer, Long>{
	
	public List<Transfer> retrieveTransfers(Date dateFrom, Date dateTo) throws DaoException;
	

	/**
	 * Link and create both transfers
	 * 
	 * @param transfer1
	 * @param transfer2
	 * @return both transfers recently created. It preserves the order: 
	 * 		transfers[0]: tranfer1
	 * 		transfers[1]: tranfer2 
	 * 
	 * @throws DaoException
	 */
	public List<Transfer> createRecords(Transfer transfer1, Transfer transfer2) throws DaoException, InvalidOperationException;


	public List<Transfer> retrieveTransfers(ReservationForm reservationForm) throws DaoException;
}

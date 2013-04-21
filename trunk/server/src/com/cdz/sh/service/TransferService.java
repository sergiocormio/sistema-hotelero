package com.cdz.sh.service;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Transfer;

/**
 * Facade for Transfer entity
 * 
 * @author fede
 *
 */
public interface TransferService extends CrudService<Transfer, Long>{
	
	public List<Transfer> retrieveTransfers(Date dateFrom, Date dateTo) throws DaoException;
	

}

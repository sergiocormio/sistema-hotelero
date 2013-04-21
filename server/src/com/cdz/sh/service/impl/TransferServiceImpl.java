package com.cdz.sh.service.impl;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.TransferDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.TransferDaoImpl;
import com.cdz.sh.model.Transfer;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.TransferService;

/**
 * Implementation of TransferService facade
 * 
 * @author fede
 *
 */
public class TransferServiceImpl extends AbstractCrudService<Transfer, Long> implements TransferService {

	private TransferDao transferDao;
	
	@Override
	protected CrudDao<Transfer, Long> createDao() {
		this.transferDao = new TransferDaoImpl();
		return this.transferDao;
	}

	@Override
	public List<Transfer> retrieveTransfers(Date dateFrom, Date dateTo) throws DaoException {
		return this.transferDao.retrieveTransfers(dateFrom, dateTo);
	}
	

}

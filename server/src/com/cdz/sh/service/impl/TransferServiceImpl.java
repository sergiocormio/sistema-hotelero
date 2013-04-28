package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.TransferDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.TransferDaoImpl;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Transfer;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.TransferService;
import com.cdz.sh.service.exception.InvalidOperationException;

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

	@Override
	public List<Transfer> createRecords(Transfer transfer1, Transfer transfer2)
			throws DaoException, InvalidOperationException {
		List<Transfer> createdTransfers = new ArrayList<Transfer>(); 
		
		transfer1 = this.crudDao.createRecord(transfer1);
		
		transfer2.setRelatedTransfer(transfer1);
		
		transfer2 = this.crudDao.createRecord(transfer2);
		
		transfer1.setRelatedTransfer(transfer2);
		
		this.crudDao.updateRecord(transfer1);
		
		createdTransfers.add(transfer1);
		createdTransfers.add(transfer2);
		
		return createdTransfers;
	}

	@Override
	public void updateRecord(Transfer e) throws DaoException {
		
		this.crudDao.updateRecord(e);
		if(e.getRelatedTransfer() != null){
			this.crudDao.updateRecord(e.getRelatedTransfer());
		}
	}

	@Override
	public void deleteRecord(Transfer e) throws DaoException {
		
		if(e.getRelatedTransfer() == null){
			this.crudDao.deleteRecord(e);
		}
		else{
			Transfer relatedTransfer = e.getRelatedTransfer();
			
			relatedTransfer.setRelatedTransfer(null);
			e.setRelatedTransfer(null);
			
			this.crudDao.updateRecord(relatedTransfer);
			this.crudDao.updateRecord(e);
			
			this.crudDao.deleteRecord(relatedTransfer);
			this.crudDao.deleteRecord(e);
		}
	}

	@Override
	public List<Transfer> retrieveTransfers(ReservationForm reservationForm) throws DaoException{
		
		return this.transferDao.retrieveTransfers(reservationForm);
	}
	
	
	
	

}

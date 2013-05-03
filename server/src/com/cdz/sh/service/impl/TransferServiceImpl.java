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
	public void updateRecord(Transfer newTransfer) throws DaoException {
				
		Transfer originalTransfer = this.crudDao.getRecordById(newTransfer.getId());
		
		try {
			// if it was round trip and now is one way
			if(originalTransfer.isRoundTrip() && newTransfer.isOneWay()){
				
				//unlink and remove both original round trip transfers
				this.deleteRecord(originalTransfer);
				
				// create the new one way one
				
				// as it is one way, just in case...
				newTransfer.setId(null);
				newTransfer.setRelatedTransfer(null);
				
				this.createRecord(newTransfer);
			}
			// if it was one way and now is round trip
			if(originalTransfer.isOneWay() && newTransfer.isRoundTrip()){
				// remove the original single transfer
				this.deleteRecord(originalTransfer);
				
				// create the both new transfers
				Transfer relatedTransfer = newTransfer.getRelatedTransfer();

				//this is to avoid dup PK
				newTransfer.setId(null);
				relatedTransfer.setId(null);
				
				//this is to avoid PersistenceTransientException
				newTransfer.setRelatedTransfer(null);
				relatedTransfer.setRelatedTransfer(null);
				
				this.createRecords(newTransfer, relatedTransfer);
			}
			// it remains being one way
			// or
			// it remains being round trip
			else{
				this.crudDao.updateRecord(newTransfer);
				if(newTransfer.getRelatedTransfer() != null){
					this.crudDao.updateRecord(newTransfer.getRelatedTransfer());
				}
			}
		}
		catch (InvalidOperationException e1) {
			throw new DaoException(e1.getMessage());
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

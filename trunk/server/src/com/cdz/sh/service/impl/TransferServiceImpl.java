package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
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

	@Override
	protected CrudDao<Transfer, Long> createDao() {
		return new TransferDaoImpl();
	}


	
	

}

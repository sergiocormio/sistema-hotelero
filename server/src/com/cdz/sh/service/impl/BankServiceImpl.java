package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.BankDaoImpl;
import com.cdz.sh.model.Bank;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.BankService;

/**
 * Implementation of BankService facade
 * 
 * @author fede
 *
 */
public class BankServiceImpl extends AbstractCrudService<Bank, Long> implements BankService {

	@Override
	protected CrudDao<Bank, Long> createDao() {
		return new BankDaoImpl();
	}

}

package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ActivationDaoImpl;
import com.cdz.sh.model.Server;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ActivationService;

/**
 * Implementation of BankService facade
 * 
 * @author fede
 *
 */
public class ActivationServiceImpl extends AbstractCrudService<Server, Long> implements ActivationService {

	@Override
	protected CrudDao<Server, Long> createDao() {
		return new ActivationDaoImpl();
	}

}

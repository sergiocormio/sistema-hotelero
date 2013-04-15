package com.cdz.sh.service.impl;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.ServiceTypeDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.exception.SHException;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.List;
import com.cdz.sh.service.ServiceTypeService;

/**
 * Implementation of ServiceTypeService facade
 * 
 * @author fede
 *
 */
public class ServiceTypeServiceImpl extends AbstractCrudService<ServiceType, Long> implements ServiceTypeService {

	private ServiceTypeDao serviceTypeDao;
	
	@Override
	protected CrudDao<ServiceType, Long> createDao() {
		this.serviceTypeDao = new ServiceTypeDaoImpl();
		return this.serviceTypeDao;
	}

	@Override
	public void deleteRecord(ServiceType e) throws DaoException {
		if(e.isTransfer())
		{
			throw new DaoException(ExceptionErrorCodes.INVALID_OPERATION, "This record is a trasfer and can not be deleted");
		}
		super.deleteRecord(e);
	}

	
	public List<ServiceType> getTransferServiceTypes() throws DaoException {
		return this.serviceTypeDao.getTransferServiceTypes();
		
	}
	
	
	

}

package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.CleaningTypeDaoImpl;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CleaningTypeService;

/**
 * Implementation of CleaningTypeService facade
 * 
 * @author fede
 *
 */
public class CleaningTypeServiceImpl extends AbstractCrudService<CleaningType, Long> implements CleaningTypeService {

	
	@Override
	protected CrudDao<CleaningType, Long> createDao() {
		return new CleaningTypeDaoImpl();
	}
	
	
	

}

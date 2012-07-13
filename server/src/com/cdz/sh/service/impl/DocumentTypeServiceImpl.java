package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.DocumentTypeService;

/**
 * Implementation of DocumentTypeService facade
 * 
 * @author sergiocormio
 *
 */
public class DocumentTypeServiceImpl extends AbstractCrudService<DocumentType, Long> implements DocumentTypeService {

	@Override
	protected CrudDao<DocumentType, Long> createDao() {
		return new DocumentTypeDaoImpl();
	}

	
}

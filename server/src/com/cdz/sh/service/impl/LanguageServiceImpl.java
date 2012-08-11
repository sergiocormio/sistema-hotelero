package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.model.Language;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.LanguageService;

/**
 * Implementation of LanguageService facade
 * 
 * @author fede
 *
 */
public class LanguageServiceImpl extends AbstractCrudService<Language, Long> implements LanguageService {

	@Override
	protected CrudDao<Language, Long> createDao() {
		return new LanguageDaoImpl();
	}
	

}

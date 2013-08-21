package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.EmailTemplateDaoImpl;
import com.cdz.sh.model.EmailTemplate;
import com.cdz.sh.model.EmailTemplatePK;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.EmailTemplateService;

/**
 * Implementation of CleaningService facade
 * 
 * @author fede
 *
 */
public class EmailTemplateServiceImpl extends AbstractCrudService<EmailTemplate, EmailTemplatePK> implements EmailTemplateService {


	@Override
	protected CrudDao<EmailTemplate, EmailTemplatePK> createDao() {
		return new EmailTemplateDaoImpl();
	}
	
	
	

}

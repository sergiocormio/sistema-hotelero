package com.cdz.sh.service.impl;

import com.cdz.sh.dao.impl.PromotionDaoImpl;
import com.cdz.sh.model.Promotion;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.PromotionService;

/**
 * Implementation of PromotionService facade
 * 
 * @author fede
 *
 */
public class PromotionServiceImpl extends AbstractCrudService<Promotion, Long> implements PromotionService {

	public PromotionServiceImpl() {
		this.crudDao = new PromotionDaoImpl();
	}

	
	
	

}

package com.cdz.sh.service;

import com.cdz.sh.exception.SHException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Promotion;

/**
 * Facade for Promotion entity
 * 
 * @author fede
 *
 */
public interface PromotionService extends CrudService<Promotion, Long>{
	
	
	public Alternative checkPromotions(Alternative alternative) throws SHException;

}

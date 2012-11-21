package com.cdz.sh.service;

import com.cdz.sh.model.Budget;
import com.cdz.sh.model.ExchangeRate;

/**
 * Facade for ExchangeRate entity
 * 
 * @author fede
 *
 */
public interface ExchangeRateService extends CrudService<ExchangeRate, String>{
	
	
	/**
	 * TODO: declare specific behavior
	 */

	public Budget convertTo(Budget budget, ExchangeRate exchangeRate);
}

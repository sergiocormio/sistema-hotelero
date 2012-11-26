package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.service.exception.NoRateException;

public interface BudgetService {
	
	/**
	 * Creates the budget of the given alternative
	 * 
	 * @param alternative
	 * @return the given alternative with the related budget set
	 */
	public Alternative populateBudget(Alternative alternative) throws DaoException, NoRateException;
	
	/**
	 * Populates the budgets of the given alternatives
	 * 
	 * @param alternatives
	 * @return the given alternatives with the related budgets set
	 */
	public List<Alternative> populatesBudgets(List<Alternative> alternatives) throws DaoException, NoRateException;

	
	/**
	 * Exports the given budget object in a PDF file
	 *  
	 * @param budget
	 * @param selectedLocale: Possible values 'es_AR', 'pt_BR', 'en_US'.
	 * @return
	 * @throws DaoException
	 */
	public byte[] exportData(Budget budget, String selectedLocale, ExchangeRate exchangeRate) throws DaoException; 
}

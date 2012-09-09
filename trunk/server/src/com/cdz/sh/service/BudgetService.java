package com.cdz.sh.service;

import java.util.List;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;

public interface BudgetService {
	
	/**
	 * Creates the budget of the given alternative
	 * 
	 * @param alternative
	 * @return the given alternative with the related budget set
	 */
	public Alternative populateBudget(Alternative alternative) throws DaoException;
	
	/**
	 * Populates the budgets of the given alternatives
	 * 
	 * @param alternatives
	 * @return the given alternatives with the related budgets set
	 */
	public List<Alternative> populatesBudgets(List<Alternative> alternatives) throws DaoException;

}

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
	 * @return
	 */
	public Budget getBudget(Alternative alternative) throws DaoException;
	
	/**
	 * Creates the budgets of the given alternatives
	 * 
	 * @param alternatives
	 * @return
	 */
	public List<Budget> getBudget(List<Alternative> alternatives) throws DaoException;

}

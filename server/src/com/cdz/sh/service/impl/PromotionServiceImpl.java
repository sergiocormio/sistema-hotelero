package com.cdz.sh.service.impl;

import java.util.List;

import com.cdz.sh.dao.PromotionDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.PromotionDaoImpl;
import com.cdz.sh.exception.SHException;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Promotion;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.PromotionService;
import com.cdz.sh.service.exception.NoRateException;

/**
 * Implementation of PromotionService facade
 * 
 * @author fede
 *
 */
public class PromotionServiceImpl extends AbstractCrudService<Promotion, Long> implements PromotionService {

	private PromotionDao promotionDao;
	
	
	@Override
	protected CrudDao<Promotion, Long> createDao() {
		this.promotionDao = new PromotionDaoImpl();
		return this.promotionDao;
	}

	
	@Override
	public Alternative checkPromotions(Alternative alternative) throws SHException {
	
		List<Promotion> promotions = this.promotionDao.retrieveContainedPromotions(alternative.getDateFrom(), alternative.getDateTo());
		
		if(promotions.isEmpty()){
			return alternative;
		}
		
		alternative = checkPromotions(alternative, promotions);

		if(alternative.hasPromotion()){
			
			alternative = addBudget(alternative);
		}
		
		return alternative;
	}



	/**
	 * Checks if the given alternative matches with an existing promotion. If so, sets the promotion
	 * 
	 * @param alternative
	 * @param promotions
	 * @return
	 * @throws DaoException
	 * @throws NoRateException
	 */
	public Alternative checkPromotions(Alternative alternative, List<Promotion> promotions) throws DaoException, NoRateException {

		for (Promotion promotion : promotions) {
			
			if(alternative.getDateFrom().equals(promotion.getDateFrom()) &&
				alternative.getDateTo().equals(promotion.getDateTo()) &&
				alternative.getOccupations().get(0).getId().getRoom().getRoomType().equals(promotion.getRoomType())){
				
				alternative.setPromotion(promotion);
			}
		}
		
		return alternative;
	}
	

	private Alternative addBudget(Alternative alternative) throws DaoException, NoRateException {
		
		BudgetService budgetService = new BudgetServiceImpl();
		
		return budgetService.populateBudget(alternative);
	}
}

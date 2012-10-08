package com.cdz.sh.service.impl;

import java.util.List;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.RateDao;
import com.cdz.sh.dao.ServiceTypeDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RoomType;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.exception.NoRateException;

public class BudgetServiceImpl implements BudgetService {

	private RateDao rateDao;
	private CrudDao<ServiceType, Long> serviceTypeDao;
	
	public BudgetServiceImpl(){
		this.rateDao = new RateDaoImpl();
		this.serviceTypeDao = new ServiceTypeDaoImpl();
	}

	@Override
	public Alternative populateBudget(Alternative alternative) throws DaoException, NoRateException {
		
		Budget budget = new Budget(alternative);
		
		Double basePrice = new Double(0);
		RoomType roomType = null;
		Rate rate = null;
		
		for (Occupation occupation : alternative.getOccupations()) {
			RoomType nextRoomType = occupation.getId().getRoom().getRoomType();
			
			if(!nextRoomType.equals(roomType)){
				roomType = nextRoomType;
				rate = this.rateDao.retrieveRate(nextRoomType, occupation.getId().getDate());
				if(rate == null){
					throw new NoRateException(ExceptionErrorCodes.NO_RATE, "There is no rate that matches with the given room type and/or the date range.");
				}
			}
			
			basePrice += rate.getPrice();
		}
		budget.setBasePrice(basePrice);
		
		
		List<ServiceType> additionalServiceTypes = ((ServiceTypeDao)this.serviceTypeDao).retrieveAdditionalServices();
		budget.setAdditionalServices(additionalServiceTypes);
		
		List<ServiceType> servicesIncludedInBasePrice = ((ServiceTypeDao)this.serviceTypeDao).retrieveServicesIncludedInBasePrice();
		budget.setServicesToBeAddedInBasePrice(servicesIncludedInBasePrice);
		
		budget.calculateServicesInCombinationWithBasePrice();
		
		alternative.setBudget(budget);
		
		return alternative;
	}



	@Override
	public List<Alternative> populatesBudgets(List<Alternative> alternatives) throws DaoException, NoRateException {
		
		for (Alternative alternative : alternatives) {
			this.populateBudget(alternative);
		}
		return alternatives;
	}

}

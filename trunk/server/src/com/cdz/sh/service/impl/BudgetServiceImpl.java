package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cdz.sh.constants.MasterDataConstants;
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

public class BudgetServiceImpl implements BudgetService {

	private RateDao rateDao;
	private CrudDao<ServiceType, Long> serviceTypeDao;
	
	public BudgetServiceImpl(){
		this.rateDao = new RateDaoImpl();
		this.serviceTypeDao = new ServiceTypeDaoImpl();
	}

	@Override
	public Budget getBudget(Alternative alternative) throws DaoException {
		
		Budget budget = new Budget();
		
		Double basePrice = new Double(0);
		Double priceWithBreakfast = new Double(0);
		Double priceWithParking = new Double(0);
		Double priceWithBreakfastAndParking = new Double(0);
		
		RoomType roomType = null;
		Rate rate = null;
		
		ServiceType breakfast = this.serviceTypeDao.getRecordById(MasterDataConstants.SERVICE_TYPE_BREAKFAST);
		ServiceType parking = this.serviceTypeDao.getRecordById(MasterDataConstants.SERVICE_TYPE_PARKING);
		
		for (Occupation occupation : alternative.getOccupations()) {
			RoomType nextRoomType = occupation.getId().getRoom().getRoomType();
			
			if(!nextRoomType.equals(roomType)){
				roomType = nextRoomType;
				rate = this.rateDao.retrieveRate(nextRoomType, occupation.getId().getDate());
			}
			basePrice += rate.getPrice();
			priceWithBreakfast += rate.getPrice() + breakfast.getPrice();
			priceWithParking += rate.getPrice() + parking.getPrice();
			priceWithBreakfastAndParking += rate.getPrice() + breakfast.getPrice() + parking.getPrice();
		}
		
		budget.setBasePrice(basePrice);
		budget.setPriceWithBreakfast(priceWithBreakfast);
		budget.setPriceWithParking(priceWithParking);
		budget.setPriceWithBreakfastAndParking(priceWithBreakfastAndParking);
		
		List<ServiceType> additionalServiceTypes = ((ServiceTypeDao)this.serviceTypeDao).retrieveAdditionalServices();
		budget.setAdditionalServices(additionalServiceTypes);
		
		return budget;
	}



	@Override
	public List<Budget> getBudget(List<Alternative> alternatives) throws DaoException {
		List<Budget> budgets = new ArrayList<Budget>();
		for (Alternative alternative : alternatives) {
			Budget budget = this.getBudget(alternative);
			budgets.add(budget);
		}
		return budgets;
	}

}

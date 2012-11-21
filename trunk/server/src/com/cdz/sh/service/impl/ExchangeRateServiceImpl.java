package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.ServiceAddedInBasePrice;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ExchangeRateService;


/**
 * Implementation of ExchangeRateService facade
 * 
 * @author fede
 *
 */
public class ExchangeRateServiceImpl extends AbstractCrudService<ExchangeRate, String> implements ExchangeRateService {


	@Override
	protected CrudDao<ExchangeRate, String> createDao() {
		return new ExchangeRateDaoImpl();
	}

	@Override
	public Budget convertTo(Budget budget, ExchangeRate exchangeRate) {
		
		Budget convertedBudget = new Budget();
		
		convertedBudget.setBasePrice(budget.getBasePrice() * exchangeRate.getValueAgainstReal());
		
		convertedBudget.setBasePricePlusAllServicesIncludedInBasePrice(budget.getBasePricePlusAllServicesIncludedInBasePrice() * exchangeRate.getValueAgainstReal());
		
		
		List<ServiceType> convertedServiceTypes = new ArrayList<ServiceType>();
		
		for (ServiceType serviceType : budget.getAdditionalServices()) {
			ServiceType convertedServiceType = new ServiceType();
		
			convertedServiceType.setName(serviceType.getName());
			convertedServiceType.setPrice(serviceType.getPrice() * exchangeRate.getValueAgainstReal());
			convertedServiceType.setId(serviceType.getId());
			convertedServiceType.setDescription(serviceType.getDescription());
			convertedServiceType.setIncludedInBasePrice(serviceType.isIncludedInBasePrice());
			convertedServiceType.setIncludedInBudget(serviceType.isIncludedInBudget());
			
			convertedServiceTypes.add(convertedServiceType);
		}
		
		convertedBudget.setAdditionalServices(convertedServiceTypes);
		
		
		List<ServiceAddedInBasePrice> convertedServicesAddedInBasePrice = new ArrayList<ServiceAddedInBasePrice>();
		
		for (ServiceAddedInBasePrice serviceAddedInBasePrice : budget.getServicePricesAddedInBasePrice()) {
			ServiceAddedInBasePrice convertedServiceAddedInBasePrice = new ServiceAddedInBasePrice();
		
			convertedServiceAddedInBasePrice.setServiceType(serviceAddedInBasePrice.getServiceType());
			convertedServiceAddedInBasePrice.setPrice(serviceAddedInBasePrice.getPrice() * exchangeRate.getValueAgainstReal());
			
			convertedServicesAddedInBasePrice.add(convertedServiceAddedInBasePrice);
		}
		
		convertedBudget.setServicePricesAddedInBasePrice(convertedServicesAddedInBasePrice);
		
		return convertedBudget;
	}

	
	
	

}

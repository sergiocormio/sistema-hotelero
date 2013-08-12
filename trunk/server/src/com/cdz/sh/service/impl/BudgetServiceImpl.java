package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.RateDao;
import com.cdz.sh.dao.ServiceTypeDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Budget;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RoomType;
import com.cdz.sh.model.ServiceAddedInBasePrice;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.model.export.ExportBudget;
import com.cdz.sh.model.export.ExportServiceAddedInBasePrice;
import com.cdz.sh.model.export.ExportServiceType;
import com.cdz.sh.report.PDFReportManager;
import com.cdz.sh.service.BudgetService;
import com.cdz.sh.service.exception.NoRateException;
import com.cdz.sh.util.DateUtil;
import com.cdz.sh.util.PriceFormater;

public class BudgetServiceImpl implements BudgetService {

	private static final String BUDGET_TEMPLATE = "budget";
	private static final String TEMPLATE_FILE_TYPE = ".jrxml";
	
	private RateDao rateDao;
	private CrudDao<ServiceType, Long> serviceTypeDao;
	
	public BudgetServiceImpl(){
		this.rateDao = new RateDaoImpl();
		this.serviceTypeDao = new ServiceTypeDaoImpl();
	}

	@Override
	public Alternative populateBudget(Alternative alternative) throws DaoException, NoRateException {
		
		Budget budget = new Budget(alternative);
		
		RoomType roomType = null;
		Double basePricePerDay = new Double(0);
		int daysQuantity = 0;
		boolean firstOccupation = true;
		Double basePrice = new Double(0);
		Rate rate = null;
		
		daysQuantity = DateUtil.getDaysQuantity(alternative.getDateFrom(), alternative.getDateTo());
		if(alternative.hasPromotion()){
			
			roomType = alternative.getPromotion().getRoomType();
			
			basePrice = alternative.getPromotion().getPrice();
		
			basePricePerDay = basePrice / daysQuantity;
		}
		else{
			for (Occupation occupation : alternative.getOccupations()) {
				RoomType nextRoomType = occupation.getId().getRoom().getRoomType();
				
				if(!nextRoomType.equals(roomType)){
					roomType = nextRoomType;
					rate = this.rateDao.retrieveRate(nextRoomType, occupation.getId().getDate());
					if(rate == null){
						throw new NoRateException(ExceptionErrorCodes.NO_RATE, "There is no rate that matches with the given room type and/or the date range.");
					}
				}
				
				if(firstOccupation){
					// the price per day ad roomtype will be the ones related to
					// the first occupation
					// see what to do with alternatives that have different rates/prices/roomtypes...
					roomType = rate.getId().getRoomType();
					basePricePerDay = rate.getPrice();
					firstOccupation = false;
				}
				basePrice += rate.getPrice();
			}
		}
		
		budget.setRoomType(roomType);
		budget.setPricePerDay(basePricePerDay);
		budget.setDaysQuantity(daysQuantity);
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
	public List<Alternative> populateBudgets(List<Alternative> alternatives) throws DaoException, NoRateException {
		
		for (Alternative alternative : alternatives) {
			this.populateBudget(alternative);
		}
		return alternatives;
	}
	
	
	
	@Override
	public byte[] exportData(Budget budget, String selectedLocale, ExchangeRate exchangeRate) throws DaoException{
	
		ExportBudget exportBudget = createExportBudget(budget, exchangeRate);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String filename = BUDGET_TEMPLATE + "_" + selectedLocale + TEMPLATE_FILE_TYPE;
		
		PDFReportManager pdfReportManager = new PDFReportManager(filename, params);
		
		Collection<ExportBudget> collection = new ArrayList<ExportBudget>();
		collection.add(exportBudget);
		
		byte[] report = pdfReportManager.createReport(collection);
		
		return report;
	}

	private ExportBudget createExportBudget(Budget budget, ExchangeRate exchangeRate) {
		
		ExportBudget exportBudget = new ExportBudget();
		
		exportBudget.setDateFrom(DateUtil.getDateUTC(budget.getRelatedAlternative().getDateFrom()));
		exportBudget.setDateTo(DateUtil.getDateUTC(budget.getRelatedAlternative().getDateTo()));
		exportBudget.setRoomType(budget.getRoomType());
		exportBudget.setPricePerDay( PriceFormater.formatPrice(budget.getPricePerDay(), exchangeRate) );
		exportBudget.setDaysQuantity( budget.getDaysQuantity() );
		exportBudget.setBasePrice( PriceFormater.formatPrice(budget.getBasePrice(), exchangeRate) );
		
		exportBudget.setBasePricePlusAllServicesIncludedInBasePrice(PriceFormater.formatPrice(budget.getBasePricePlusAllServicesIncludedInBasePrice(), exchangeRate));
		
		
		List<ExportServiceType> exportServiceTypes = new ArrayList<ExportServiceType>();
		
		for (ServiceType serviceType : budget.getAdditionalServices()) {
			ExportServiceType exportServiceType = new ExportServiceType();
		
			exportServiceType.setName(serviceType.getName());
			exportServiceType.setPrice(PriceFormater.formatPrice(serviceType.getPrice(), exchangeRate));
			
			exportServiceTypes.add(exportServiceType);
		}
		
		exportBudget.setAdditionalServices(exportServiceTypes);
		
		
		List<ExportServiceAddedInBasePrice> exportServicesAddedInBasePrice = new ArrayList<ExportServiceAddedInBasePrice>();
		
		for (ServiceAddedInBasePrice serviceAddedInBasePrice : budget.getServicePricesAddedInBasePrice()) {
			ExportServiceAddedInBasePrice exportServiceAddedInBasePrice = new ExportServiceAddedInBasePrice();
		
			ServiceType serviceType = serviceAddedInBasePrice.getServiceType();
			
			ExportServiceType exportServiceType = new ExportServiceType();
			exportServiceType.setName(serviceType.getName());
			exportServiceType.setPrice(PriceFormater.formatPrice(serviceType.getPrice(), exchangeRate));
			
			exportServiceAddedInBasePrice.setServiceType(exportServiceType);
			
			exportServiceAddedInBasePrice.setPrice(PriceFormater.formatPrice(serviceAddedInBasePrice.getPrice(), exchangeRate));
			
			exportServicesAddedInBasePrice.add(exportServiceAddedInBasePrice);
		}
		
		exportBudget.setServicePricesAddedInBasePrice(exportServicesAddedInBasePrice);
		
		return exportBudget;
		
	}
	
	

}

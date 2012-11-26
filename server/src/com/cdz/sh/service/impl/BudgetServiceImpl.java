package com.cdz.sh.service.impl;

import java.text.DecimalFormat;
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
		
		exportBudget.setBasePrice( formatPrice(budget.getBasePrice(), exchangeRate) );
		
		exportBudget.setBasePricePlusAllServicesIncludedInBasePrice(formatPrice(budget.getBasePricePlusAllServicesIncludedInBasePrice(), exchangeRate));
		
		
		List<ExportServiceType> exportServiceTypes = new ArrayList<ExportServiceType>();
		
		for (ServiceType serviceType : budget.getAdditionalServices()) {
			ExportServiceType exportServiceType = new ExportServiceType();
		
			exportServiceType.setName(serviceType.getName());
			exportServiceType.setPrice(formatPrice(serviceType.getPrice(), exchangeRate));
			
			exportServiceTypes.add(exportServiceType);
		}
		
		exportBudget.setAdditionalServices(exportServiceTypes);
		
		
		List<ExportServiceAddedInBasePrice> exportServicesAddedInBasePrice = new ArrayList<ExportServiceAddedInBasePrice>();
		
		for (ServiceAddedInBasePrice serviceAddedInBasePrice : budget.getServicePricesAddedInBasePrice()) {
			ExportServiceAddedInBasePrice exportServiceAddedInBasePrice = new ExportServiceAddedInBasePrice();
		
			ServiceType serviceType = serviceAddedInBasePrice.getServiceType();
			
			ExportServiceType exportServiceType = new ExportServiceType();
			exportServiceType.setName(serviceType.getName());
			exportServiceType.setPrice(formatPrice(serviceType.getPrice(), exchangeRate));
			
			exportServiceAddedInBasePrice.setServiceType(exportServiceType);
			
			exportServiceAddedInBasePrice.setPrice(formatPrice(serviceAddedInBasePrice.getPrice(), exchangeRate));
			
			exportServicesAddedInBasePrice.add(exportServiceAddedInBasePrice);
		}
		
		exportBudget.setServicePricesAddedInBasePrice(exportServicesAddedInBasePrice);
		
		return exportBudget;
		
	}
	
	private String formatPrice(Double price, ExchangeRate exchangeRate){
		return exchangeRate.getCurrencySymbol() + " " + new DecimalFormat("#.00").format(price);
	}

}

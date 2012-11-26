package com.cdz.sh.model.export;

import java.util.ArrayList;
import java.util.List;

import com.cdz.sh.model.ExchangeRate;

public class ExportBudget {
	
	private String basePrice;
	
	private List<ExportServiceAddedInBasePrice> servicePricesAddedInBasePrice;

	private List<ExportServiceType> additionalServices;
	
	private String basePricePlusAllServicesIncludedInBasePrice;
	
	private ExchangeRate exchangeRate;
	
	public ExportBudget(){
		this.additionalServices = new ArrayList<ExportServiceType>();
		this.servicePricesAddedInBasePrice = new ArrayList<ExportServiceAddedInBasePrice>();
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public List<ExportServiceAddedInBasePrice> getServicePricesAddedInBasePrice() {
		return servicePricesAddedInBasePrice;
	}

	public void setServicePricesAddedInBasePrice(
			List<ExportServiceAddedInBasePrice> servicePricesAddedInBasePrice) {
		this.servicePricesAddedInBasePrice = servicePricesAddedInBasePrice;
	}

	public List<ExportServiceType> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<ExportServiceType> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getBasePricePlusAllServicesIncludedInBasePrice() {
		return basePricePlusAllServicesIncludedInBasePrice;
	}

	public void setBasePricePlusAllServicesIncludedInBasePrice(
			String basePricePlusAllServicesIncludedInBasePrice) {
		this.basePricePlusAllServicesIncludedInBasePrice = basePricePlusAllServicesIncludedInBasePrice;
	}

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
}

package com.cdz.sh.model.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.RoomType;

public class ExportBudget {
	
	private Date dateFrom;
	private Date dateTo;
	private RoomType roomType;
	private String pricePerDay;
	private int daysQuantity;
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

	public String getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(String pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public int getDaysQuantity() {
		return daysQuantity;
	}

	public void setDaysQuantity(int daysQuantity) {
		this.daysQuantity = daysQuantity;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	

	
	
}

package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.List;

public class Budget {
	
	private Alternative relatedAlternative;
	
	private Double basePrice;
	private Double priceWithBreakfast;
	private Double priceWithParking;
	private Double priceWithBreakfastAndParking;
	
	private List<ServiceType> additionalServices;

	
	public Budget(){
		this.additionalServices = new ArrayList<ServiceType>();
	}
	
	
	
	public Alternative getRelatedAlternative() {
		return relatedAlternative;
	}


	public void setRelatedAlternative(Alternative relatedAlternative) {
		this.relatedAlternative = relatedAlternative;
	}


	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getPriceWithBreakfast() {
		return priceWithBreakfast;
	}

	public void setPriceWithBreakfast(Double priceWithBreakfast) {
		this.priceWithBreakfast = priceWithBreakfast;
	}

	public Double getPriceWithParking() {
		return priceWithParking;
	}

	public void setPriceWithParking(Double priceWithParking) {
		this.priceWithParking = priceWithParking;
	}

	public Double getPriceWithBreakfastAndParking() {
		return priceWithBreakfastAndParking;
	}

	public void setPriceWithBreakfastAndParking(Double priceWithBreakfastAndParking) {
		this.priceWithBreakfastAndParking = priceWithBreakfastAndParking;
	}

	public List<ServiceType> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<ServiceType> additionalServices) {
		this.additionalServices = additionalServices;
	}
	
	public void setAdditionalServices(ServiceType additionalService) {
		this.additionalServices.add(additionalService);
	}
	
}

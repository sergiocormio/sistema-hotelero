package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.List;

public class Budget {
	
	private Alternative relatedAlternative;
	
	private float basePrice;
	private float priceWithBreakfast;
	private float priceWithParking;
	private float priceWithBreakfastAndParking;
	
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


	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public float getPriceWithBreakfast() {
		return priceWithBreakfast;
	}

	public void setPriceWithBreakfast(float priceWithBreakfast) {
		this.priceWithBreakfast = priceWithBreakfast;
	}

	public float getPriceWithParking() {
		return priceWithParking;
	}

	public void setPriceWithParking(float priceWithParking) {
		this.priceWithParking = priceWithParking;
	}

	public float getPriceWithBreakfastAndParking() {
		return priceWithBreakfastAndParking;
	}

	public void setPriceWithBreakfastAndParking(float priceWithBreakfastAndParking) {
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

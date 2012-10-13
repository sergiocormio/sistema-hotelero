package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Budget {
	
	private Alternative relatedAlternative;
	
	private Double basePrice;
		
	/*
	 * The price of these ones have to be calculated according to the serciveTypeModality
	 */
	private List<ServiceType> servicesToBeAddedInBasePrice;
	
	/*
	 * There is no need in calculating the price of the ones below, because they will not be added in the basePrice
	 */
	private List<ServiceType> additionalServices;
	
	private Map<ServiceType, Double> servicePriceAddedInBasePriceMap;

	private Double basePricePlusAllServicesIncludedInBasePrice;

	
	public Budget(Alternative alternative){
		this.relatedAlternative = alternative;
		this.additionalServices = new ArrayList<ServiceType>();
		this.servicesToBeAddedInBasePrice = new ArrayList<ServiceType>();
		this.servicePriceAddedInBasePriceMap = new HashMap<ServiceType, Double>();
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

	
	public List<ServiceType> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<ServiceType> additionalServices) {
		this.additionalServices = additionalServices;
	}
	
	public void setAdditionalServices(ServiceType additionalService) {
		this.additionalServices.add(additionalService);
	}

	public List<ServiceType> getServicesToBeAddedInBasePrice() {
		return servicesToBeAddedInBasePrice;
	}

	public void setServicesToBeAddedInBasePrice(List<ServiceType> servicesToBeAddedInBasePrice) {
		this.servicesToBeAddedInBasePrice = servicesToBeAddedInBasePrice;
	}
	
	
	public Map<ServiceType, Double> getServicePriceAddedInBasePriceMap() {
		return servicePriceAddedInBasePriceMap;
	}



	public Double getBasePricePlusAllServicesIncludedInBasePrice() {
		return basePricePlusAllServicesIncludedInBasePrice;
	}


	
	/**
	 * ONLY BLAZEDS PURPOSES
	 * 
	 * @param servicePriceAddedInBasePriceMap
	 */
	public void setServicePriceAddedInBasePriceMap(
			Map<ServiceType, Double> servicePriceAddedInBasePriceMap) {
		this.servicePriceAddedInBasePriceMap = servicePriceAddedInBasePriceMap;
	}


	/**
	 * ONLY BLAZEDS PURPOSES
	 * 
	 * @param basePricePlusAllServicesIncludedInBasePrice
	 */
	public void setBasePricePlusAllServicesIncludedInBasePrice(Double basePricePlusAllServicesIncludedInBasePrice) {
		this.basePricePlusAllServicesIncludedInBasePrice = basePricePlusAllServicesIncludedInBasePrice;
	}



	public void calculateServicesInCombinationWithBasePrice(){
		
		this.calculateServicesIncludedInBasePrice();
		
		this.calculateBasePricePlusAllServicesIncludedInBasePrice();
	}
	
	private void calculateServicesIncludedInBasePrice(){

		for (ServiceType serviceType : this.servicesToBeAddedInBasePrice) {
			
			Double basePricePlusService = this.basePrice;
			basePricePlusService += getCalculatedPrice(serviceType);
			
			this.servicePriceAddedInBasePriceMap.put(serviceType, basePricePlusService);
		}
	
	}
	
	private void calculateBasePricePlusAllServicesIncludedInBasePrice(){
		this.basePricePlusAllServicesIncludedInBasePrice = this.basePrice;
		for(ServiceType serviceType : servicesToBeAddedInBasePrice){
			
			this.basePricePlusAllServicesIncludedInBasePrice += getCalculatedPrice(serviceType);
			
		}
	}
	
	
	private Double getCalculatedPrice(ServiceType serviceType){
		
		int nights = this.relatedAlternative.getOccupations().size();
		
		if(serviceType.getModality().equals(ServiceTypeModality.PER_PERSON)){
			
			return serviceType.getPrice() * nights * relatedAlternative.getPeopleQuantity();
		}
		else if(serviceType.getModality().equals(ServiceTypeModality.PER_NIGHT)){
			
			return serviceType.getPrice() * nights;
		}
		
		return null;
	}
}

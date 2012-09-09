package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.List;

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

	
	public Budget(Alternative alternative){
		this.relatedAlternative = alternative;
		this.additionalServices = new ArrayList<ServiceType>();
		this.servicesToBeAddedInBasePrice = new ArrayList<ServiceType>();
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
	
	public ServiceType getServiceIncludedInBasePrice(int index){
		return this.servicesToBeAddedInBasePrice.get(index);
	}

	public Double getServicePriceAddedInBasePrice(int index){
		ServiceType serviceType = this.servicesToBeAddedInBasePrice.get(index);
		ServiceTypeModality modality = serviceType.getModality();
		
		double basePricePlusService = this.basePrice;
		basePricePlusService += getCalculatedPrice(serviceType);
		
		return basePricePlusService;
	}
	
	public Double getBasePricePlusAllServicesIncludedInBasePrice(){
		Double basePricePlusAllServicesIncludedInBasePrice = this.basePrice;
		for(ServiceType serviceType : servicesToBeAddedInBasePrice){
			
			basePricePlusAllServicesIncludedInBasePrice += getCalculatedPrice(serviceType);
			
		}
		return basePricePlusAllServicesIncludedInBasePrice;
	}
	
	
	private Double getCalculatedPrice(ServiceType serviceType){
		
		int nights = this.relatedAlternative.getOccupations().size();
		
		if(serviceType.getModality().equals(ServiceTypeModality.porPersona)){
			
			return serviceType.getPrice() * nights * relatedAlternative.getPeopleQuantity();
		}
		else if(serviceType.getModality().equals(ServiceTypeModality.porNoche)){
			
			return serviceType.getPrice() * nights;
		}
		
		return null;
	}
}

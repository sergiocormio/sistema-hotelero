package com.cdz.sh.model;

/**
 * To be used for storing calculated prices for each service type included
 * in budget, in base price.
 * 
 * @author fede
 *
 */
public class ServiceAddedInBasePrice {
	
	private ServiceType serviceType;
	
	private Double price;

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}

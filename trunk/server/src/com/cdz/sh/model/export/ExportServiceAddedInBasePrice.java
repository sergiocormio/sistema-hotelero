package com.cdz.sh.model.export;

/**
 * To be used for storing calculated prices for each service type included
 * in budget, in base price.
 * 
 * @author fede
 *
 */
public class ExportServiceAddedInBasePrice {
	
	private ExportServiceType serviceType;
	
	private String price;

	public ExportServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ExportServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}

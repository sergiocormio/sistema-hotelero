package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeRate {

	/*
	 * Is the currencyCode 
	 */
	@Id
	private String id;
	
	private String name;
	
	private String currencySymbol;
    
	private Double valueAgainstReal;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Double getValueAgainstReal() {
		return valueAgainstReal;
	}

	public void setValueAgainstReal(Double valueAgainstReal) {
		this.valueAgainstReal = valueAgainstReal;
	}
    	
    
}

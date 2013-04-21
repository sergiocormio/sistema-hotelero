package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceType {

	@Id
	@GeneratedValue
	private Long id;	
	
	private String name;
	
	private String description;
	
	private TransferType transferType;
	
	/**
	 * if it will be seen in the budget
	 */
	private boolean includedInBudget;
	
	/**
	 * if it will be added to the base price, in the budget
	 */
	private boolean includedInBasePrice;
	
	private ServiceTypeModality modality;
	
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public boolean isIncludedInBudget() {
		return includedInBudget;
	}

	public void setIncludedInBudget(boolean includedInBudget) {
		this.includedInBudget = includedInBudget;
	}

	public boolean isIncludedInBasePrice() {
		return includedInBasePrice;
	}

	public void setIncludedInBasePrice(boolean includedInBasePrice) {
		this.includedInBasePrice = includedInBasePrice;
	}

	public ServiceTypeModality getModality() {
		return modality;
	}

	public void setModality(ServiceTypeModality modality) {
		this.modality = modality;
	}

	public TransferType getTransferType() {
		return transferType;
	}

	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}


	

	

	
}

package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceType {

	@Id
	@GeneratedValue
	private Long id;	//we have to know the id of serviceTypes such as Breakfast and Parking
	
	private String name;
	
	private String description;
	
	private boolean additionalFixed;
	
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

	public boolean isAdditionalFixed() {
		return additionalFixed;
	}

	public void setAdditionalFixed(boolean additionalFixed) {
		this.additionalFixed = additionalFixed;
	}

	
	
}

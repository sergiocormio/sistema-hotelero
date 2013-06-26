package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
    @GeneratedValue
    private Long id;
	
	private String street;
	private String city;
	private String state;
	
	@ManyToOne
	@JoinColumn(name="REGION_ID")
    private Region region;
    
	private String zipCode;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
			
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	


	@Override
	public String toString() {
		
		String toString = "Id: " + this.getId() + "\n" +
				          "street: " + street + "\n" +
				          "city: " + city + "\n" +
				          "state: " + state + "\n" +
				          "zipcode: " + zipCode + "\n";
		if(region != null){
			toString = toString.concat("Region: \n " + this.getRegion().toString() + "\n");
		}
		
		return toString;
	}
		
}

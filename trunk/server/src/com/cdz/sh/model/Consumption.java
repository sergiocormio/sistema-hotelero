package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Consumption {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@ManyToOne
//	@JoinColumns(value = {@JoinColumn(name = "CUSTOMER_DOC_TYPE"), @JoinColumn(name = "CUSTOMER_ID_NUMBER")} )
//	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private Room room;
	
//	@ManyToOne
//	@JoinColumn(name="SERVICE_TYPE_ID")
//	private ServiceType serviceType;
	
	private Date date;
	
	private String description;
	
	private double price;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

//	public ServiceType getServiceType() {
//		return serviceType;
//	}
//
//	public void setServiceType(ServiceType serviceType) {
//		this.serviceType = serviceType;
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    	

	
}

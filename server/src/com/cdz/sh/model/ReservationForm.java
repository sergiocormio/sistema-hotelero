package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class ReservationForm {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumns(value = {@JoinColumn(name = "CUSTOMER_DOC_TYPE"), @JoinColumn(name = "CUSTOMER_ID_NUMBER")} )
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="STATE_RESERVATION_FORM_ID")
	private StateReservationForm state;
	
	private Date creationDate;
	
	private Date dateFrom;
	
	private Date dateTo;
	
    private int adultsQuantity;
    
    private int childrenQuantity;
    
    private float pricePerDay;
    
    private float monetaryReserve;
    
    @ManyToOne
	@JoinColumn(name="Bank_ID")
    private Bank bank;
    
    private String bankDocumentNumber;

	    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public StateReservationForm getState() {
		return state;
	}

	public void setState(StateReservationForm state) {
		this.state = state;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date from) {
		this.dateFrom = from;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date to) {
		this.dateTo = to;
	}

	public int getAdultsQuantity() {
		return adultsQuantity;
	}

	public void setAdultsQuantity(int adultsQuantity) {
		this.adultsQuantity = adultsQuantity;
	}

	public int getChildrenQuantity() {
		return childrenQuantity;
	}

	public void setChildrenQuantity(int childrenQuantity) {
		this.childrenQuantity = childrenQuantity;
	}

	public float getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(float pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public float getMonetaryReserve() {
		return monetaryReserve;
	}

	public void setMonetaryReserve(float monetaryReserve) {
		this.monetaryReserve = monetaryReserve;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getBankDocumentNumber() {
		return bankDocumentNumber;
	}

	public void setBankDocumentNumber(String bankDocumentNumber) {
		this.bankDocumentNumber = bankDocumentNumber;
	}

	
	
}

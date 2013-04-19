package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {

	@Id
	@GeneratedValue
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="RESERVATION_FORM_ID")
	private ReservationForm reservationForm;
	
	@ManyToOne
	@JoinColumn(name="SERVICE_TYPE_ID")
	private ServiceType serviceType;
	
	private String source;

    private String flightOrBusNumber;
    
    private String companyName;
    
    private Date date; //YYY-MM-DD-HH:mm

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public ReservationForm getReservationForm() {
		return reservationForm;
	}

	public void setReservationForm(ReservationForm reservationForm) {
		this.reservationForm = reservationForm;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFlightOrBusNumber() {
		return flightOrBusNumber;
	}

	public void setFlightOrBusNumber(String flightOrBusNumber) {
		this.flightOrBusNumber = flightOrBusNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
    
}

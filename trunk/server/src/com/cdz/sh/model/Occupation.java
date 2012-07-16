package com.cdz.sh.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Occupation {
	
	@EmbeddedId
	private OccupationPK id; 
	
	@ManyToOne
	@JoinColumn(name="RESERVATION_FORM_ID")
	private ReservationForm reservationForm;

	
	public OccupationPK getId() {
		return id;
	}

	public void setId(OccupationPK id) {
		this.id = id;
	}

	public ReservationForm getReservationForm() {
		return reservationForm;
	}

	public void setReservationForm(ReservationForm reservationForm) {
		this.reservationForm = reservationForm;
	}
		
    

}

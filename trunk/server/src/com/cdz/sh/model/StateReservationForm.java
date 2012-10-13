package com.cdz.sh.model;


public enum StateReservationForm {
	
	
	PRE_BOOKING("Pre-reserva"),
	CONFIRMED("Confirmada"),
	CANCELLED("Cancelada"),
	EXPIRED("Vencida");
	
	private String displayname;
	
	private StateReservationForm(String displayname){
		this.displayname = displayname;
	}

	public String getDisplayname() {
		return displayname;
	}
	
	
}


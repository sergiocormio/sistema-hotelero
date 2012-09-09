package com.cdz.sh.model;


public enum StateReservationForm {
	

	pre_reserva("Pre-Reserva"),
	confirmada("Confirmada"),
	cancelada("Cancelada"),
	vencida("Vencida");

	private String displayName;

	private StateReservationForm(String displayName){
		this.displayName = displayName;
	}
	
	public String getDisplayName(){
		return this.displayName;
	}

}

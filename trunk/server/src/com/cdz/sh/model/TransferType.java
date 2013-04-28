package com.cdz.sh.model;

public enum TransferType {

	ONE_WAY_BUS,
	ROUND_TRIP_BUS,
	ONE_WAY_FLIGHT,
	ROUND_TRIP_FLIGHT;
	
	
	public String getName(){
		return this.name();
	}
}

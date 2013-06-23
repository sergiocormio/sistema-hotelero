package com.cdz.sh.model;

public enum CleaningType {
	   
	GENERAL,	// first day
	BASIC,		// every other day except from Sunday
	BED_CLOTHE_CHANGE;
		 
	public String getName(){
		return this.name();
	}
}
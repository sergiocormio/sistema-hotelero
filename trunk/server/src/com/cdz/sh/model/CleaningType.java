package com.cdz.sh.model;

public enum CleaningType {
	   
	GENERAL,	// first day
	BASIC,		// every other day except from Sunday
	BED_CLOTHE_CHANGE;
		 
	public String getName(){
		return this.name();
	}
	
	@Override
	public String toString() {
		String result = "UNKNOWN";
		switch(this){
		case GENERAL:
			result = "GENERAL";
			break;
		case BASIC:
			result = "BASIC";
			break;
		case BED_CLOTHE_CHANGE:
			result = "BED_CLOTHE_CHANGE";
			break;
		}
		return result;
	}
}
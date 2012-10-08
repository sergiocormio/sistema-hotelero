package com.cdz.sh.model.request;

import java.util.Date;

public class CheckAvailabilityRequest {

	private Date dateFrom;
	private Date dateTo;
	private int adultsQty;
	private int childrenQty;
	private boolean withMaritalBed;
	
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public int getAdultsQty() {
		return adultsQty;
	}
	public void setAdultsQty(int adultsQty) {
		this.adultsQty = adultsQty;
	}
	public int getChildrenQty() {
		return childrenQty;
	}
	public void setChildrenQty(int childrenQty) {
		this.childrenQty = childrenQty;
	}
	public boolean isWithMaritalBed() {
		return withMaritalBed;
	}
	public void setWithMaritalBed(boolean withMaritalBed) {
		this.withMaritalBed = withMaritalBed;
	}
	
	
}

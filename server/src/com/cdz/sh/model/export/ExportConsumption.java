package com.cdz.sh.model.export;

import java.util.Date;
import java.util.List;

import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.util.PriceFormater;

public class ExportConsumption {

	private Date dateFrom;
	private Date dateTo;
	
	private ReservationForm form;
	
	private double totalPrice;
	
	private List<Consumption> consumptions;
	
	

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

	public List<Consumption> getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(List<Consumption> consumptions) {
		this.consumptions = consumptions;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	


	public String getTotalPriceFormatted() {
		return PriceFormater.formatPrice(totalPrice);
	}
	
}

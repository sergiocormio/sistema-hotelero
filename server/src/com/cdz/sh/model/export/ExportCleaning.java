package com.cdz.sh.model.export;

import java.util.Date;
import java.util.List;

import com.cdz.sh.model.Cleaning;

public class ExportCleaning {

	private Date date;
	private List<Cleaning> cleanings;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Cleaning> getCleanings() {
		return cleanings;
	}
	public void setCleanings(List<Cleaning> cleanings) {
		this.cleanings = cleanings;
	}
	
	
	
}

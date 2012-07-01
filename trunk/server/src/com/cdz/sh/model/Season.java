package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Season {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Date dateFrom;
	
	private Date dateTo;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date from) {
		this.dateFrom = from;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date to) {
		this.dateTo = to;
	}
	
	
}

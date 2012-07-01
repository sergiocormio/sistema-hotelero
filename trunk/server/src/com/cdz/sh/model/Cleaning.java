package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Cleaning {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumns(value = {@JoinColumn(name = "OCCUPATION_DATE"), @JoinColumn(name = "OCCUPATION_ROOM_ID")} )
	private Occupation occupation;
	
	@ManyToOne
	@JoinColumn(name="CLEANING_TYPE_ID")
	private CleaningType cleaningType;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public CleaningType getCleaningType() {
		return cleaningType;
	}

	public void setCleaningType(CleaningType cleaningType) {
		this.cleaningType = cleaningType;
	}
			
}

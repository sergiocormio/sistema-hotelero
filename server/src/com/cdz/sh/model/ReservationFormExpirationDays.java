package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReservationFormExpirationDays {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int daysToExpire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		
	public int getDaysToExpire() {
		return daysToExpire;
	}

	public void setDaysToExpire(int daysToExpire) {
		this.daysToExpire = daysToExpire;
	}

	@Override
	public String toString() {
		
		return "id: " + this.getId() + "\n" +
				"days: " + this.getDaysToExpire();
	}
	

}

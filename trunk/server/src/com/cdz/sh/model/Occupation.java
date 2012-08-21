package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Occupation {
	
	@EmbeddedId
	private OccupationPK id; 
	
	
	public Occupation(){}
	
	public Occupation(Date date, Room room, ReservationForm form){
		OccupationPK occupationId = new OccupationPK();
		occupationId.setDate(date);
		occupationId.setRoom(room);
		occupationId.setReservationForm(form);
		this.id = occupationId;
	}
	
	public OccupationPK getId() {
		return id;
	}

	public void setId(OccupationPK id) {
		this.id = id;
	}

	
	public int hashCode() {
		return (int) this.getId().hashCode();
	}
    
	public boolean equals(Object obj) {
       	if(obj instanceof Occupation){
        	Occupation anotherOcc = (Occupation) obj;
    		return this.getId().equals(anotherOcc.getId());
        }
    	else{
    		return false;
    	}
    }
    
    @Override
	public String toString() {
		return this.getId().toString();
	}

	
}

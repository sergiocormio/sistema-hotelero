package com.cdz.sh.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;


@Entity
public class Cleaning {

	@EmbeddedId
	private CleaningPK id;
	
	@ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @Enumerated(value = EnumType.STRING)
	private Set<String> assignedCleaning = new HashSet<String>();
	
	public Cleaning(){
	}
	
	public Cleaning(Date date, Room room){
		id = new CleaningPK();
		id.setDate(date);
		id.setRoom(room);
	}
	
	public void setId(CleaningPK id) {
		this.id = id;
	}
	public CleaningPK getId() {
		return id;
	}
	public void setAssignedCleaning(Set<String> assignedCleaning) {
		this.assignedCleaning = assignedCleaning;
	}
	public Set<String> getAssignedCleaning() {
		return assignedCleaning;
	}
	public void addCleaningType(CleaningType cleaningType) {
		if(assignedCleaning == null){
			assignedCleaning = new HashSet<String>();
		}
		assignedCleaning.add(cleaningType.toString());
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof Cleaning){
			Cleaning otherObj = (Cleaning) obj;
			if(this.getId().equals(otherObj.getId())){
				//verifies if they have the same list of CleaningTypes
				if(this.getAssignedCleaning().equals(otherObj.getAssignedCleaning())){
					return true;
				}
			}
		}
		return false;
	}
}

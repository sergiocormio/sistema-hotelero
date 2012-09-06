package com.cdz.sh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Room {
	
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number", unique = true) 
    private int number;

	private String description;
    
	@ManyToOne
	@JoinColumn(name="ROOM_TYPE_ID")
    private RoomType roomType;
    
    private int peopleQuantity;    
    
    private boolean withMaritalBed;
    
    private boolean withExtraBed; 
    

    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
    
    public int getNumber() {
    	return number;
    }
    
    public void setNumber(int number) {
    	this.number = number;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public RoomType getRoomType() {
    	return roomType;
    }
    
    public void setRoomType(RoomType roomType) {
    	this.roomType = roomType;
    }
            
    public int getPeopleQuantity() {
		return peopleQuantity;
	}

	public void setPeopleQuantity(int peopleQuantity) {
		this.peopleQuantity = peopleQuantity;
	}

	public boolean isWithMaritalBed() {
    	return withMaritalBed;
    }
    
    public void setWithMaritalBed(boolean withMaritalBed) {
    	this.withMaritalBed = withMaritalBed;
    }
    
    public boolean isWithExtraBed() {
    	return withExtraBed;
    }
    
    public void setWithExtraBed(boolean withExtraBed) {
    	this.withExtraBed = withExtraBed;
    }
    
         
    
    public int hashCode() {
        return this.number;
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof Room){
        	
    		Room pk = (Room) obj;
        	
        	return pk.getNumber() == this.number;
        }
    	else{
    		return false;
    	}
    	
    }
}
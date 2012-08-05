package com.cdz.sh.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RatePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	@ManyToOne
	@JoinColumn(name="ROOM_TYPE_ID")
	private RoomType roomType;
	
	@ManyToOne
	@JoinColumn(name="SEASON_ID")
	private Season season;
	
	
	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public int hashCode() {
        return (int) roomType.hashCode() + season.hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof RatePK){
        	
        	RatePK pk = (RatePK) obj;
        	
        	return pk.getRoomType().equals(roomType) && pk.getSeason().equals(season);
        }
    	else{
    		return false;
    	}
    	
    }
    
    @Override
	public String toString() {
		
		String toString = "RoomType: " + this.getRoomType().toString() + "\n" ;
		toString = toString.concat("Season: " + this.getSeason() + "\n");
		
		return toString;
	}
}

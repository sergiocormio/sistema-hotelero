package com.cdz.sh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cdz.sh.util.DateUtil;

@Embeddable
public class CleaningPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1373807675939937335L;

	private Date date;
	
	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private Room room;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}
	
	@Override
	public int hashCode() {
		return date.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof CleaningPK){
			CleaningPK otherObj = (CleaningPK) obj;
			if (this.getDate().equals(otherObj.getDate()) && this.getRoom().equals(otherObj.getRoom())){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		String toString = "";
		if(date != null){
			toString = toString.concat("Date: " + DateUtil.dateToStringYYYYmmDD(this.getDate()) + " - " );
		}
		if(room != null){
			toString = toString.concat("Room: " + this.getRoom().getId() + " - ");
		}
		return toString + "\n";
	}
}

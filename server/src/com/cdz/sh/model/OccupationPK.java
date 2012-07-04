package com.cdz.sh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cdz.sh.util.DateUtil;

@Embeddable
public class OccupationPK implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Date date;
	
	@ManyToOne
	@JoinColumn(name="ROOM_ID")
	private Room room;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	public int hashCode() {
		String strDate = DateUtil.dateToStringYYYYmmDD(this.getDate());
        return (int) room.hashCode() + strDate.hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof OccupationPK){
        	
    		OccupationPK pk = (OccupationPK) obj;
    		String thisStrDate = DateUtil.dateToStringYYYYmmDD(this.getDate());
    		String pkStrDate = DateUtil.dateToStringYYYYmmDD(pk.getDate());
        	
    		return pk.getRoom().equals(room) && pkStrDate.equals(thisStrDate);
        }
    	else{
    		return false;
    	}
    	
    }
}

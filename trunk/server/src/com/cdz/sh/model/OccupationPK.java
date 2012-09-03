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

	@ManyToOne
	@JoinColumn(name="RESERVATION_FORM_ID")
	private ReservationForm reservationForm;
	
	
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
	
	public ReservationForm getReservationForm() {
		return reservationForm;
	}

	public void setReservationForm(ReservationForm reservationForm) {
		this.reservationForm = reservationForm;
	}
	
	public int hashCode() {
		String strDate = DateUtil.dateToStringYYYYmmDD(this.getDate());
		int rfHashCode = 0;
		if(reservationForm != null){
			rfHashCode = this.getReservationForm().hashCode();
		}
        return (int) room.hashCode() + strDate.hashCode() + rfHashCode;
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof OccupationPK){
        	
    		OccupationPK pk = (OccupationPK) obj;
    		String thisStrDate = DateUtil.dateToStringYYYYmmDD(this.getDate());
    		String pkStrDate = DateUtil.dateToStringYYYYmmDD(pk.getDate());
    		boolean equalsRfs = true;
    		if(reservationForm != null && pk.getReservationForm() != null){
    			equalsRfs = this.getReservationForm().equals(pk.getReservationForm());
    		}
    		return pk.getRoom().equals(room) && pkStrDate.equals(thisStrDate) && equalsRfs;
        }
    	else{
    		return false;
    	}
    	
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
		if(reservationForm != null){
			toString = toString.concat("Reservation Form: " + this.getReservationForm().getId());
		}
		return toString + "\n";
	}
}

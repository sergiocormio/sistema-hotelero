package com.cdz.sh.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"ROOM_TYPE_ID","SEASON_ID"}), name="RATE")
public class Rate {

//	@Id
//	@GeneratedValue
//	private Long id;

	@EmbeddedId
	private RatePK id;
	
	
	private float price;
	
		
	public RatePK getId() {
		return id;
	}

	public void setId(RatePK id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	public int hashCode() {
        return (int)this.getId().hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof Rate){
        	
        	Rate anotherRate = (Rate) obj;
        	
        	return anotherRate.getId().equals(id);
        }
    	else{
    		return false;
    	}
    	
    }
	
}

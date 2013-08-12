package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;


@Entity
public class RoomType {
	
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Type(type="text")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof RoomType){
        	
			RoomType anotherRoomType = (RoomType) obj;
    		
    		return anotherRoomType.getId().equals(this.getId());
        }
    	else{
    		return false;
    	}
	}
    	    		
	
	
		
}
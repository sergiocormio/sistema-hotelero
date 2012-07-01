package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"ROOM_TYPE_ID","SEASON_ID"}), name="RATE")
public class Rate {

	@Id
	@GeneratedValue
	private Long id;

	private float price;
	
	@ManyToOne
	@JoinColumn(name="ROOM_TYPE_ID")
	private RoomType roomType;
	
	@ManyToOne
	@JoinColumn(name="SEASON_ID")
	private Season season;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

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
	
	
	
}

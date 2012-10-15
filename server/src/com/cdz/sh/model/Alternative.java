package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alternative implements Comparable<Alternative>{

	private int peopleQuantity;
	
	private List<Occupation> occupations;
	
	private Budget budget;

	private Room lastRoom;
	private int roomChanges;
	private int newRoomAvailableDays;
	
	private List<Room> distinctRooms;
	
	
	public Alternative(){}
			
	
	public Alternative(int peopleQuantity){
		
		this.peopleQuantity = peopleQuantity;
		
		occupations = new ArrayList<Occupation>();
		roomChanges = 0;
		lastRoom = null;
		newRoomAvailableDays = 0;
		distinctRooms = new ArrayList<Room>();
	}
		

	public List<Occupation> getOccupations() {
		return occupations;
	}

	public void addOccupation(Occupation occupation) {
		
		this.occupations.add(occupation);
		
		Room room = occupation.getId().getRoom();
		
		updateDistinctRooms(room);
		updateRoomChanges(room);
		updateValidRoomChange(room);
		
		if(lastRoom == null || !room.equals(lastRoom)){
			lastRoom = room;
		}
	}
	
	
	private void updateDistinctRooms(Room room) {

		if(this.distinctRooms.isEmpty()){
			this.distinctRooms.add(room);
		}
		else {
			Room lastRoom = this.distinctRooms.get(this.distinctRooms.size()-1);
			if(lastRoom.getNumber() != room.getNumber()){
				this.distinctRooms.add(room);
			}
		}
	}


	private void updateValidRoomChange(Room room) {
		if(lastRoom != null && roomChanges > 0){
			if(room.equals(lastRoom)){
				newRoomAvailableDays++;
			}
			else{
				newRoomAvailableDays = 1; // the first day that this "target" room is empty	
			}
		}
	}

	private void updateRoomChanges(Room room){
		if(lastRoom != null && !room.equals(lastRoom)){
			roomChanges++;
		}
	}

	public Date getDateFrom() {
		if(occupations != null && !occupations.isEmpty()){
			return occupations.get(0).getId().getDate();
		}
		return null;
	}

	
	public Date getDateTo() {
		if(occupations != null && !occupations.isEmpty()){
			return occupations.get(occupations.size()-1).getId().getDate();
		}
		return null;
	}

	
	public int getPeopleQuantity() {
		return peopleQuantity;
	}


	public void setPeopleQuantity(int peopleQuantity) {
		this.peopleQuantity = peopleQuantity;
	}


	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
	public int getRoomChanges() {
		return roomChanges;
	}
	
	
	
	
	public Room getLastRoom() {
		return lastRoom;
	}

	

	public int getNewRoomAvailableDays() {
		return newRoomAvailableDays;
	}


	public void setNewRoomAvailableDays(int newRoomAvailableDays) {
		this.newRoomAvailableDays = newRoomAvailableDays;
	}


	public void setLastRoom(Room lastRoom) {
		this.lastRoom = lastRoom;
	}


	public void setRoomChanges(int roomChanges) {
		this.roomChanges = roomChanges;
	}


	/**
	 * A room change is valid when the target room is empty for no more than 3 days. If the target room is empty for more than
	 * 3 days, Nacho prefers to keep it for future customers. The idea is to minimize the gaps between reservations.
	 * 
	 * @return
	 */
	public boolean hasValidRoomChanges(){
		return (this.newRoomAvailableDays <= 3);
	}
	
	public boolean hasValidNumberOfRoomChanges(){
		return (this.roomChanges < 3);
	}
	
	
	public List<Room> getDistinctRooms() {
		return distinctRooms;
	}


	@Override
	public Alternative clone() {
		Alternative clonedAlternative = new Alternative(this.peopleQuantity);
		
		clonedAlternative.setBudget(this.getBudget());
		for (Occupation occupation : this.occupations) {
			clonedAlternative.addOccupation(occupation);
		}
		clonedAlternative.setLastRoom(this.lastRoom);
		clonedAlternative.setNewRoomAvailableDays(this.newRoomAvailableDays);
		clonedAlternative.setRoomChanges(this.roomChanges);
		
		return clonedAlternative;
	}


	@Override
	public int compareTo(Alternative anotherAlternative) {
		if(this.getBudget().getBasePrice() < anotherAlternative.getBudget().getBasePrice()){
			return -1;
		}
		else if(this.getBudget().getBasePrice() > anotherAlternative.getBudget().getBasePrice()){
			return 1;
		}
		else{
			if(this.getRoomChanges() < anotherAlternative.getRoomChanges()){
				return -1;
			}
			else if(this.getRoomChanges() > anotherAlternative.getRoomChanges()){
				return 1;
			}
			else{
				return 0;
			}
		}
	}


	@Override
	public String toString() {
		
		String toString = "Alternative [Base Price = " + budget.getBasePrice() + "\n";
		toString = toString.concat("\t     Room Changes = " + roomChanges + "\n");
		
		toString = toString.concat("\t     Occupations [ \n");
		for (Occupation occupation : this.occupations) {
			toString = toString.concat("\t\t" + occupation.toString());
		}
		toString = toString.concat("\t     ]\n");
		toString = toString.concat("]\n\n");
		
		return toString;
	}

	

//	public int calculateRoomChanges() {
//		int roomChanges = 0;
//		int lastRoomNumber = -1;
//		for (Occupation occupation : this.occupations) {
//			int currentRoomNumber = occupation.getId().getRoom().getNumber();
//			if(lastRoomNumber == -1){
//				lastRoomNumber = currentRoomNumber;
//			}
//			else{
//				if(currentRoomNumber != lastRoomNumber){
//					roomChanges++;
//					lastRoomNumber = currentRoomNumber;
//				}
//			}
//		}
//		return roomChanges;
//	}

	//This setter is just for BlazeDS
	public void setOccupations(List<Occupation> occupations) {
		this.occupations = occupations;
	}

	//This setter is just for BlazeDS
	public void setDistinctRooms(List<Room> distinctRooms) {
		this.distinctRooms = distinctRooms;
	}

}

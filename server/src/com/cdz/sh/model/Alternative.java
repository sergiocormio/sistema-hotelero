package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alternative implements Comparable<Alternative>{

	private List<Occupation> occupations;
	
	private Budget budget;

	private Room lastRoom;
	private int roomChanges;
	private int newRoomAvailableDays;
	
	
	public Alternative(){
		occupations = new ArrayList<Occupation>();
		roomChanges = 0;
		lastRoom = null;
		newRoomAvailableDays = 0;
	}
		

	public List<Occupation> getOccupations() {
		return occupations;
	}

	public void addOccupation(Occupation occupation) {
		if(this.occupations == null){
			this.occupations = new ArrayList<Occupation>();
		}
		this.occupations.add(occupation);
		
		Room room = occupation.getId().getRoom();
		updateRoomChanges(room);
		updateValidRoomChange(room);
	}
	
	private void updateValidRoomChange(Room room) {
		if(lastRoom == null){
			lastRoom = room;
		}
		else if(roomChanges > 0){
			if(room.equals(lastRoom)){
				newRoomAvailableDays++;
			}
			else{
				newRoomAvailableDays = 0;	
			}
		}
	}

	private void updateRoomChanges(Room room){
		if(lastRoom == null){
			lastRoom = room;
		}
		else{
			if(!room.equals(lastRoom)){
				roomChanges++;
				lastRoom= room;
			}
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


	/**
	 * A room change is valid when the target room is empty for no more than 3 days. If the target room is empty for more than
	 * 3 days, Nacho prefers to keep it for future customers. The idea is to minimize the gaps between reservations.
	 * 
	 * @return
	 */
	public boolean hasValidRoomChanges(){
		return (this.newRoomAvailableDays <= 3);
	}

	@Override
	public Alternative clone() {
		Alternative clonedAlternative = new Alternative();
		
		clonedAlternative.setBudget(this.getBudget());
		for (Occupation occupation : this.occupations) {
			clonedAlternative.addOccupation(occupation);
		}
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
	
	
		
}

package com.cdz.sh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alternative implements Comparable<Alternative>{

	private List<Occupation> occupations;
	
	private Budget budget;
		

	public List<Occupation> getOccupations() {
		return occupations;
	}

	public void setOccupations(List<Occupation> occupations) {
		this.occupations = occupations;
	}
	
	public void addOccupation(Occupation occupation) {
		if(this.occupations == null){
			this.occupations = new ArrayList<Occupation>();
		}
		this.occupations.add(occupation);
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

	@Override
	public Alternative clone() {
		Alternative clonedAlternative = new Alternative();
		
		clonedAlternative.setBudget(this.getBudget());
		for (Occupation occupation : this.occupations) {
			clonedAlternative.addOccupation(occupation);
		}
		return clonedAlternative;
	}

	public int getRoomChanges() {
		int roomChanges = 0;
		int lastRoomNumber = -1;
		for (Occupation occupation : this.occupations) {
			int currentRoomNumber = occupation.getId().getRoom().getNumber();
			if(lastRoomNumber == -1){
				lastRoomNumber = currentRoomNumber;
			}
			else{
				if(currentRoomNumber != lastRoomNumber){
					roomChanges++;
					lastRoomNumber = currentRoomNumber;
				}
			}
		}
		return roomChanges;
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

	/*
	 * A room change is valid when the target room is empty for no more than 3 days. If the target room is empty for more than
	 * 3 days, Nacho prefers to keep it for future customers. The idea is to minimize the gaps between reservations.
	 * 
	 */
	public boolean hasValidRoomChanges() {
		// now it does not filter alternatives		
		return true;
	}
	
	
	
	
		
}

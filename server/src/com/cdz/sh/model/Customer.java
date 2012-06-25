package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Customer {
	
	    @Id
	    @GeneratedValue
	    private long id;

	    private String firstName;

	    private String lastName;
	    
	    private Date dateOfBirth;
	    
	    private String country;
	    
	    private String Region;
	    
	    private String email;
	    
	    private String profession;
	    
	    private Date lastLodgmentDate;
	    
	    
	    public void setId(final long id) {
	    	this.id = id;
	    }
	    public long getId() {
	    	return id;
	    }
	    
	    public void setFirstName(final String name) {
	    	this.firstName = name;
	    }
	    
	    public String getFirstName() {
	    	return firstName;
	    }
	    
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public Date getDateOfBirth() {
			return dateOfBirth;
		}
		
		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		
		public String getCountry() {
			return country;
		}
		
		public void setCountry(String country) {
			this.country = country;
		}
		
		public String getRegion() {
			return Region;
		}
		
		public void setRegion(String region) {
			Region = region;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getProfession() {
			return profession;
		}
		
		public void setProfession(String profession) {
			this.profession = profession;
		}
		
		public Date getLastLodgmentDate() {
			return lastLodgmentDate;
		}
		
		public void setLastLodgmentDate(Date lastLodgmentDate) {
			this.lastLodgmentDate = lastLodgmentDate;
		}
	    
	    
}

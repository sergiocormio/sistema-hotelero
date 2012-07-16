package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Customer {
	
    @EmbeddedId
	private CustomerPK id;
    
    private String firstName;

    private String lastName;
    
    private Date dateOfBirth;
    
    @ManyToOne
	@JoinColumn(name="REGION_ID")
    private Region region;
    
    @ManyToOne
	@JoinColumn(name="LANGUAGE_ID")
    private Language language;
    
    private String email;
    
    private String profession;
    
    private Date lastLodgementDate;
    
        
    
	

	public CustomerPK getId() {
		return id;
	}

	public void setId(CustomerPK customerPK) {
		this.id = customerPK;
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
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
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
	
	public Date getLastLodgementDate() {
		return lastLodgementDate;
	}
	
	public void setLastLodgementDate(Date lastLodgementDate) {
		this.lastLodgementDate = lastLodgementDate;
	}

			
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		
		String toString =  "PK:\n " + this.getId() + "\n" +
				"First Name: " + this.getFirstName() + "\n" +
				"Last Name: " + this.getLastName() + "\n" +
				"Email: " + this.getEmail() + "\n" +
				"Date of birth: " + this.getDateOfBirth() + "\n" + 
				"Profession: " + this.getProfession() + "\n" +
				"Last lodgement date: " + this.getLastLodgementDate() + "\n";
		if(this.getRegion() != null){
			toString = toString.concat("Region: \n " + this.getRegion().toString() + "\n");
		}
		if(this.getLanguage() != null){
			toString = toString.concat("Language: \n " + this.getLanguage().toString());
		}
		return toString;
	}
		
	@Transient
	public String getDisplayableId(){
		return this.getId().getDocType().getName() + " - " + this.getId().getIdNumber();
	}
	    
	public int hashCode() {
        return (int)this.getId().hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof Customer){
        	
        	Customer pk = (Customer) obj;
        	
        	return pk.getId().equals(id);
        }
    	else{
    		return false;
    	}
    	
    }
	    
}

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
	private CustomerPK customerPK;
    
    private String firstName;

    private String lastName;
    
    private Date dateOfBirth;
    
    @ManyToOne
	@JoinColumn(name="REGION_ID")
    private Region region;
    
    private String email;
    
    private String profession;
    
    private Date lastLodgmentDate;
    
        
    
	

	public CustomerPK getCustomerPK() {
		return customerPK;
	}

	public void setCustomerPK(CustomerPK customerPK) {
		this.customerPK = customerPK;
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
	
	public Date getLastLodgmentDate() {
		return lastLodgmentDate;
	}
	
	public void setLastLodgmentDate(Date lastLodgmentDate) {
		this.lastLodgmentDate = lastLodgmentDate;
	}

	@Override
	public String toString() {
		
		String toString =  "PK:\n " + this.getId() + "\n" +
				"First Name: " + this.getFirstName() + "\n" +
				"Last Name: " + this.getLastName() + "\n" +
				"Email: " + this.getEmail() + "\n" +
				"Date of birth: " + this.getDateOfBirth() + "\n" + 
				"Profession: " + this.getProfession() + "\n" +
				"Last lodgement date: " + this.getLastLodgmentDate() + "\n";
		if(this.getRegion() != null){
			toString = toString.concat("Region: \n " + this.getRegion().toString());
		}
		return toString;
	}
		
	@Transient
	public String getId(){
		return this.getCustomerPK().getDocType().getName() + " - " + this.getCustomerPK().getIdNumber();
	}
	    
	public int hashCode() {
        return (int)this.getCustomerPK().hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof Customer){
        	
        	Customer pk = (Customer) obj;
        	
        	return pk.getCustomerPK().equals(customerPK);
        }
    	else{
    		return false;
    	}
    	
    }
	    
}

package com.cdz.sh.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class Customer {
	
	@Id
    @GeneratedValue
    private long id;
	
  	@ManyToOne
	@JoinColumn(name="DOCUMENT_TYPE_ID")
	private DocumentType docType;
	
	private String idNumber;
	
    private String firstName;

    private String lastName;
    
    private Date dateOfBirth;
        
    @ManyToOne
	@JoinColumn(name="LANGUAGE_ID")
    private Language language;
    
    @OneToOne
	@JoinColumn(name="ADDRESS_ID")
    private Address address;
    
    @Column(name = "email", unique = true)
    private String email;
    
    private String phoneNumber;
    
    private String cellphoneNumber;
    
    
    private String profession;
    
    private Date lastLodgementDate;
    
        
    
	

	public Long getId() {
		return id;
	}

	public void setId(Long customerPK) {
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
	
		
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
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
	
	@Transient
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
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
		if(this.getAddress() != null){
			toString = toString.concat("Address: \n " + this.getAddress().toString() + "\n");
		}
		if(this.getLanguage() != null){
			toString = toString.concat("Language: \n " + this.getLanguage().toString());
		}
		return toString;
	}
		
	@Transient
	public String getDisplayableId(){
		return this.getDocType().getName() + " - " + this.getIdNumber();
	}
	    
	public int hashCode() {
        return this.getId().intValue();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof Customer){
        	
        	Customer anotherCustomer = (Customer) obj;
        	
        	return anotherCustomer.getId() == id;
        }
    	else{
    		return false;
    	}
    	
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public DocumentType getDocType() {
		return docType;
	}

	public void setDocType(DocumentType docType) {
		this.docType = docType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
    
    

	
	    
}

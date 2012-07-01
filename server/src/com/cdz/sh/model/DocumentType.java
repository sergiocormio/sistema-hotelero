package com.cdz.sh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DocumentType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	private String regExp;
	
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
	
	public String getRegExp() {
		return regExp;
	}

	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}
	
	public int hashCode() {
        return (int)String.valueOf(this.id).hashCode();
    }

    public boolean equals(Object obj) {
        
    	if(obj instanceof DocumentType){
        	
        	DocumentType docType = (DocumentType) obj;
        	
        	return docType.getId() == this.id;
        }
    	else{
    		return false;
    	}
    	
    }

	@Override
	public String toString() {
		
		return "id: " + this.getId() + "\n" +
				"Name: " + this.getName() + "\n" +
				"RegExp: " + this.getRegExp();
	}

}

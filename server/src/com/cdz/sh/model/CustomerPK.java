package com.cdz.sh.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CustomerPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="DOCUMENT_TYPE_ID")
	private DocumentType docType;
	
	private String idNumber;

	
	

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

	public int hashCode() {
        return (int) docType.hashCode() + idNumber.hashCode();
	}

    public boolean equals(Object obj) {
        
    	if(obj instanceof CustomerPK){
        	
        	CustomerPK pk = (CustomerPK) obj;
        	
        	return pk.getDocType().equals(docType) && pk.getIdNumber().equals(idNumber);
        }
    	else{
    		return false;
    	}
    	
    }
    
    @Override
	public String toString() {
		
		String toString = "";
		if(docType != null){
			toString = toString.concat("DocumentType: " + this.getDocType().toString() + "\n" );
		}
		toString = toString.concat("IdNumber: " + this.getIdNumber() + "\n");
		
		return toString;
	}
}

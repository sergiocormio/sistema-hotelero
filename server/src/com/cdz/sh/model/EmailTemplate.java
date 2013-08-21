package com.cdz.sh.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

@Entity
public class EmailTemplate {
	
	@EmbeddedId
	private EmailTemplatePK id;
	
	@Type(type="text")
	private String header;
	
	@Type(type="text")
	private String footer;
	
	public EmailTemplatePK getId() {
		return id;
	}
	
	public void setId(EmailTemplatePK id) {
		this.id = id;
	}
	
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getFooter() {
		return footer;
	}
	
	public void setFooter(String footer) {
		this.footer = footer;
	}
	

}

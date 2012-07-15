package com.cdz.sh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Language {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

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
	
	

	@Override
	public String toString() {
		
		String toString = "id: " + this.getId() + "\n" +
				"Name: " + this.getName() + "\n";
		
		return toString;
	}

}

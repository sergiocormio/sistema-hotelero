package com.cdz.sh.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmailTemplatePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1373807675939937336L;
	
	public static final String BUDGET_RESPONSE = "budgetResponse";

	private String templateId;
	
	private String locale;

		
	
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	
	@Override
	public int hashCode() {
		return templateId.hashCode() + locale.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj instanceof EmailTemplatePK){
			EmailTemplatePK otherObj = (EmailTemplatePK) obj;
			
			if (templateId.equals(otherObj.templateId) && locale.equals(otherObj.locale)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		String toString = "";
		if(templateId!= null){
			toString = toString.concat("TemplateId: " + templateId + " - " );
		}
		if(locale != null){
			toString = toString.concat("Locale: " + locale);
		}
		return toString + "\n";
	}
}

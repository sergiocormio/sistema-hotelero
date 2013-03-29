package com.cdz.sh.model.request;

import java.util.List;

public class EmailRequest {

	private String from;
	private String password;
	private List<String> toList;
	private String subject;
	private String body;
	private boolean isHtml;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public boolean isIsHtml() {
		return isHtml;
	}
	public void setIsHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}
	
	
}

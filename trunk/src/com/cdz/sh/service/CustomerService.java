package com.cdz.sh.service;

import com.cdz.sh.dao.impl.CustomerDao;

public class CustomerService {
	
	private CustomerDao customerDao;
	
	public CustomerService(){
		this.customerDao = new CustomerDao();
	}

}

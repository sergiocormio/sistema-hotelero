package com.cdz.sh.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.model.Customer;

public class TestCustomersReport {

	
	private Collection<Customer> customers;
	private Map<String, Object> params;

	@Before
	public void setUp() throws Exception {
		Customer c1 = new Customer();
		c1.setId(1L);
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Customer c2 = new Customer();
		c2.setId(2L);
		c2.setFirstName("Sergio");
		c2.setLastName("Cormio");
		c2.setDateOfBirth(new Date());
		
		this.customers = new ArrayList<Customer>();
		this.customers.add(c1);
		this.customers.add(c2);
		
		this.params = new HashMap<String, Object>();
		params.put("LOGO_URL", "Brasil-2014.jpg");		
	    params.put("P_TITULO", "Reporte de Clientes");
	    params.put("P_SUBTITULO", "Clientes actuales");
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		
		String templateFileName = "report/plantilla.jrxml";
		String pdfFileName = "report/plantilla.pdf";
		
		PDFReportManager pdfReportManager = new PDFReportManager(templateFileName, pdfFileName, params);
		pdfReportManager.createReport(customers);
	}

}

package com.cdz.sh.report;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.UniqueConstraint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.model.Customer;

public class TestCustomersReport {

	
	private Collection<Customer> customers;
	private Map<String, Object> params;

	@Before
	public void setUp() throws Exception {
		//genero la coleccion de customers
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
		
		//genero el mapa de parametros para pasar al reporte
		this.params = new HashMap<String, Object>();
		
		URL image = getClass().getResource("/resources/report/Brasil-2014.jpg");
		params.put("LOGO_URL", image.getPath());		
	    
		params.put("P_TITULO", "Reporte de Clientes");
	    params.put("P_SUBTITULO", "Clientes actuales");
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		
		URL templateFile = getClass().getResource("/resources/report/plantilla.jrxml");
		
		String pdfFileName = "C:\\pdf\\pdf.pdf";
		
		PDFReportManager pdfReportManager = new PDFReportManager(templateFile.getPath(), pdfFileName, params);
		pdfReportManager.createReport(customers);
	}

}

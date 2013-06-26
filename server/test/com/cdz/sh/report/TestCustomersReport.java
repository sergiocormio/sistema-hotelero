package com.cdz.sh.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Region;

public class TestCustomersReport {

	
	private Collection<Customer> customers;
	private Map<String, Object> params;

	@Before
	public void setUp() throws Exception {

		DocumentType docType = new DocumentType();
		docType.setId(1L);
		docType.setName("DNI");
		docType.setRegExp("ACA VA LA REGEXP");
		
		/**
		 * customer 1: Fede
		 */
		
		Customer c1 = new Customer();
		c1.setDocType(docType);
		c1.setIdNumber("33103189");
		
		c1.setFirstName("Federico");
		c1.setLastName("De Seta");
		c1.setDateOfBirth(new Date());
		
		Country country = new Country();
		country.setName("Argentina");
		
		Region region = new Region();
		region.setName("Centro");
		region.setCountry(country);
		
		//c1.setRegion(region);
		
		/**
		 * customer 2: Sergio
		 */
		Customer c2 = new Customer();
		
		c2.setDocType(docType);
		c2.setIdNumber("32XXXXXX");
		c2.setFirstName("Sergio");
		c2.setLastName("Cormio");
		c2.setDateOfBirth(new Date());
		
		this.customers = new ArrayList<Customer>();
		this.customers.add(c1);
		this.customers.add(c2);
		
		//genero el mapa de parametros para pasar al reporte
		this.params = new HashMap<String, Object>();
		
//		URL image = getClass().getResource("resources/report/Brasil-2014.jpg");
//		params.put("LOGO_URL", image.getPath());		
		params.put("LOGO_URL", "resources/report/dellosky_logo.png");
	    
		params.put("P_TITULO", "Reporte de Clientes");
	    params.put("P_SUBTITULO", "Clientes actuales");
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		
//		URL templateFile = getClass().getResource("/../resources/report/plantilla.jrxml");
		String templateFileName = "resources/report/plantilla.jrxml";
		
		String pdfFileName = "C:\\pdf\\pdf.pdf";
		
		//	PDFReportManager pdfReportManager = new PDFReportManager(templateFile.getPath(), pdfFileName, params);
			PDFReportManager pdfReportManager = new PDFReportManager(templateFileName, params);
		pdfReportManager.createReport(customers);
	}

}

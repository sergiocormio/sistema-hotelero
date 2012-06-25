package com.cdz.sh.report;

import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Assumptions:
 * 	
 * 		- templateFileName has to exist
 * 		- pdfFileName has to exist
 * 		- parameters have to contains all the keys required by the jrxml file
 * 		- the collection given by parameter has to be consistent with the contained bean fieldNames 
 * 
 * @author fede
 *
 */
public class PDFReportManager {
	
	
	private String templateFileName;
	private String pdfFileName;
	private Map<String, Object> parameters;
	
	
	
	public PDFReportManager(String templateFileName, String pdfFileName, Map<String, Object> parameters) {
		super();
		this.pdfFileName = pdfFileName;
		this.templateFileName = templateFileName;
		this.parameters = parameters;
	}



	@SuppressWarnings("rawtypes")
	public void createReport(Collection collection){
		
	    try{
	    	
	    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collection);
	    	
	    	JasperReport jasperReport = JasperCompileManager.compileReport(templateFileName);
	        
	    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	
	        JasperExportManager.exportReportToPdfFile(jasperPrint, this.pdfFileName);
	        
	        System.out.println("Done!");
	    }
	    catch (Exception e){
			e.printStackTrace();
	    }
	    
	}
	
	
}



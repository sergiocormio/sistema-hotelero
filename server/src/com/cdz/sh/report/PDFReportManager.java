package com.cdz.sh.report;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
	private Map<String, Object> parameters;
	
	
	
	public PDFReportManager(String templateFileName, Map<String, Object> parameters) {
		super();
		this.templateFileName = templateFileName;
		this.parameters = parameters;
	}



	@SuppressWarnings("rawtypes")
	public byte[] createReport(Collection collection){
		
	    try{
	    	
	    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collection);
	
	    	InputStream input = this.getClass().getResourceAsStream(templateFileName);
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            
//	    	JasperReport jasperReport = JasperCompileManager.compileReport(templateFileName);
	        
	    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	
	    	/* */
//	    	JRCsvExporter exporter = new JRCsvExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//            File destFile = new File("output.csv");
//
//			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, destFile);
//            exporter.exportReport();
            /* */
            
//	    	JasperExportManager.exportReportToPdfFile(jasperPrint, this.pdfFileName);
	    	byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

	    	System.out.println("Done!");
	    	return pdf;
	    }
	    catch (Exception e){
			e.printStackTrace();
	    }
	    return null;
	}
	
	
}



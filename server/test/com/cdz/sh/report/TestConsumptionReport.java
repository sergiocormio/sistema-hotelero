package com.cdz.sh.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.impl.ConsumptionServiceImpl;
import com.cdz.sh.util.DateUtil;

public class TestConsumptionReport {

	
	private List<Consumption> twoConsumptions;
	private List<Consumption> tenConsumptions;
	private List<Consumption> fifteenConsumptions;
	private List<Consumption> twentyConsumptions;
	
	private Map<String, Object> params;

	@Before
	public void setUp() throws Exception {

		Room r1 = new Room();
		r1.setId(1);
		r1.setNumber(1);
		
		Room r2 = new Room();
		r2.setId(2);
		r2.setNumber(2);
		
		Consumption c1 = new Consumption();
		c1.setDate(new Date());
		c1.setPrice(10.50);
		c1.setRoom(r1);
		
		Consumption c2 = new Consumption();
		c2.setDate(DateUtil.getNextDay(new Date()));
		c2.setPrice(14);
		c2.setRoom(r2);
		
		
		this.twoConsumptions = new ArrayList<Consumption>();
		this.twoConsumptions.add(c1);
		this.twoConsumptions.add(c2);
		
		//genero el mapa de parametros para pasar al reporte
		this.params = new HashMap<String, Object>();
		
		this.tenConsumptions = new ArrayList<Consumption>();
		
		for(int i = 0; i<5; i++){
			tenConsumptions.addAll(twoConsumptions);
		}
		
		this.fifteenConsumptions = new ArrayList<Consumption>();
		
		for(int i = 0; i<7; i++){
			fifteenConsumptions.addAll(twoConsumptions);
		}
		fifteenConsumptions.add(c1);
		
		this.twentyConsumptions = new ArrayList<Consumption>();
		
		for(int i = 0; i<10; i++){
			twentyConsumptions.addAll(twoConsumptions);
		}
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test2Consumptions() {
		
		byte[] output = new ConsumptionServiceImpl().exportData(twoConsumptions, "es_AR");
		
		executeReport(output, "2");
		
	}
	
	@Test
	public void test10Consumptions() {
		
		byte[] output = new ConsumptionServiceImpl().exportData(tenConsumptions, "es_AR");
		
		executeReport(output, "10");
		
	}
	
	
	@Test
	public void test15Consumptions() {
		
		byte[] output = new ConsumptionServiceImpl().exportData(twentyConsumptions, "es_AR");
		
		executeReport(output, "15");
		
	}
	
	@Test
	public void test20Consumptions() {
		
		byte[] output = new ConsumptionServiceImpl().exportData(twentyConsumptions, "es_AR");
		
		executeReport(output, "20");
		
	}

	private void executeReport(byte[] output, String clQty) {
		OutputStream out;
		try {
			out = new FileOutputStream("C:\\pdf\\out_es_AR_" + clQty + "Consumptions.pdf");
			out.write(output);
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

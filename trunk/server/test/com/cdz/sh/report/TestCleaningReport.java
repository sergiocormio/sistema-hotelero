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

import com.cdz.sh.model.Cleaning;
import com.cdz.sh.model.CleaningPK;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.model.Room;
import com.cdz.sh.service.impl.CleaningServiceImpl;

public class TestCleaningReport {

	
	private List<Cleaning> twoCleanings;
	private List<Cleaning> tenCleanings;
	private List<Cleaning> fifteenCleanings;
	private List<Cleaning> twentyCleanings;
	
	private Map<String, Object> params;

	@Before
	public void setUp() throws Exception {

		Room r1 = new Room();
		r1.setId(1);
		r1.setNumber(1);
		
		Room r2 = new Room();
		r2.setId(2);
		r2.setNumber(2);
		
		CleaningPK c1PK = new CleaningPK();
		c1PK.setDate(new Date());
		c1PK.setRoom(r1);
		
		Cleaning c1 = new Cleaning();
		c1.setId(c1PK);
		c1.addCleaningType(CleaningType.GENERAL);
		c1.addCleaningType(CleaningType.BED_CLOTHE_CHANGE);
		
		CleaningPK c2PK = new CleaningPK();
		c2PK.setDate(new Date());
		c2PK.setRoom(r2);
		
		Cleaning c2 = new Cleaning();
		c2.setId(c2PK);
		c2.addCleaningType(CleaningType.BASIC);
		c2.addCleaningType(CleaningType.BED_CLOTHE_CHANGE);
		
		
		this.twoCleanings = new ArrayList<Cleaning>();
		this.twoCleanings.add(c1);
		this.twoCleanings.add(c2);
		
		//genero el mapa de parametros para pasar al reporte
		this.params = new HashMap<String, Object>();
		
		this.tenCleanings = new ArrayList<Cleaning>();
		
		for(int i = 0; i<5; i++){
			tenCleanings.addAll(twoCleanings);
		}
		
		this.fifteenCleanings = new ArrayList<Cleaning>();
		
		for(int i = 0; i<7; i++){
			fifteenCleanings.addAll(twoCleanings);
		}
		fifteenCleanings.add(c1);
		
		this.twentyCleanings = new ArrayList<Cleaning>();
		
		for(int i = 0; i<10; i++){
			twentyCleanings.addAll(twoCleanings);
		}
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test2Cleanings() {
		
		byte[] output = new CleaningServiceImpl().exportData(twoCleanings, "es_AR");
		
		executeReport(output, "2");
		
	}
	
	@Test
	public void test10Cleanings() {
		
		byte[] output = new CleaningServiceImpl().exportData(tenCleanings, "es_AR");
		
		executeReport(output, "10");
		
	}
	
	
	@Test
	public void test15Cleanings() {
		
		byte[] output = new CleaningServiceImpl().exportData(twentyCleanings, "es_AR");
		
		executeReport(output, "15");
		
	}
	
	@Test
	public void test20Cleanings() {
		
		byte[] output = new CleaningServiceImpl().exportData(twentyCleanings, "es_AR");
		
		executeReport(output, "20");
		
	}

	private void executeReport(byte[] output, String clQty) {
		OutputStream out;
		try {
			out = new FileOutputStream("C:\\pdf\\out_es_AR_" + clQty + "Cleanings.pdf");
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

package com.cdz.sh.concurrence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.service.impl.CustomerServiceImpl;
import com.cdz.sh.service.impl.DocumentTypeServiceImpl;
import com.cdz.sh.service.impl.LanguageServiceImpl;
import com.cdz.sh.service.impl.RegionServiceImpl;

public class TestConcurrency {

	private ServiceExecutorThread docTypesThread;
	private ServiceExecutorThread languagesThread;
	private ServiceExecutorThread regionsThread;
	private ServiceExecutorThread customersThread;
	
	@Before
	public void setup(){
		MasterDataFactory masterDataFactory = new MasterDataFactory();
		masterDataFactory.createMasterData();
		
		this.docTypesThread = new ServiceExecutorThread(new DocumentTypeServiceImpl());
		this.languagesThread = new ServiceExecutorThread(new LanguageServiceImpl());
		this.regionsThread = new ServiceExecutorThread(new RegionServiceImpl());
		this.customersThread = new ServiceExecutorThread(new CustomerServiceImpl());
	}
	
	@After
	public void teardown(){
		
	}
	
	@Test
	public void test() throws InterruptedException {
		
		Thread threadDocTypes = new Thread(this.docTypesThread);
		Thread threadLanguages = new Thread(this.languagesThread);
		Thread threadRegions = new Thread(this.regionsThread);
		Thread threadCustomers = new Thread(this.customersThread);
		
		threadDocTypes.start();
		threadLanguages.start();
		threadRegions.start();
		threadCustomers.start();
		
		threadDocTypes.join();
		threadLanguages.join();
		threadRegions.join();
		threadCustomers.join();
		
	}

}

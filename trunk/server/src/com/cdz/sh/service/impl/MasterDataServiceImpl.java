package com.cdz.sh.service.impl;

import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.service.MasterDataService;

public class MasterDataServiceImpl implements MasterDataService {

	@Override
	public void createMasterData() {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();
	}

}

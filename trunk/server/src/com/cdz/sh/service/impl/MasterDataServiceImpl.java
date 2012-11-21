package com.cdz.sh.service.impl;

import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.service.MasterDataService;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllBusy_SameRoomType;

public class MasterDataServiceImpl implements MasterDataService {

	@Override
	public void createMasterData() {
		
		MasterDataFactory dataFactory = new MasterDataFactory();
		dataFactory.createMasterData();
	}

}

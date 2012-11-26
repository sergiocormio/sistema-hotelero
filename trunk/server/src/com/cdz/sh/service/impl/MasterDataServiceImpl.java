package com.cdz.sh.service.impl;

import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.service.MasterDataService;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllBusy_SameRoomType;
import com.cdz.sh.service.core.scenarios.ScenarioBuilder_AllEmpty_SameRoomType;

public class MasterDataServiceImpl implements MasterDataService {

	@Override
	public void createMasterData() {
		
//		MasterDataFactory dataFactory = new MasterDataFactory();
//		dataFactory.createMasterData();
		
		ScenarioBuilder_AllEmpty_SameRoomType scenarioBuilder_AllEmpty_SameRoomType;
		try {
			scenarioBuilder_AllEmpty_SameRoomType = new ScenarioBuilder_AllEmpty_SameRoomType();
			scenarioBuilder_AllEmpty_SameRoomType.createDummyScenario();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

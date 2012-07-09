package com.cdz.sh.service.impl;

import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Region;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.RegionService;

/**
 * Implementation of RegionService facade
 * 
 * @author fede
 *
 */
public class RegionServiceImpl extends AbstractCrudService<Region, Long> implements RegionService {

	public RegionServiceImpl() {
		this.crudDao = new RegionDaoImpl();
	}

	
	
	

}

package com.cdz.sh.service.impl;

import java.util.List;

import com.cdz.sh.dao.RegionDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Country;
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

	private RegionDao regionDao;
	
	@Override
	protected CrudDao<Region, Long> createDao() {
		this.regionDao = new RegionDaoImpl();
		
		return this.regionDao;
	}
	
	@Override
	public List<Region> retrieveRegions(Country country) throws DaoException{
		return this.regionDao.retrieveRegions(country);
	}
}

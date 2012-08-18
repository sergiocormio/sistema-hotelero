package com.cdz.sh.service.impl;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.impl.SeasonDaoImpl;
import com.cdz.sh.model.Season;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.SeasonService;

/**
 * Implementation of SeasonService facade
 * 
 * @author fede
 *
 */
public class SeasonServiceImpl extends AbstractCrudService<Season, Long> implements SeasonService {

	@Override
	protected CrudDao<Season, Long> createDao() {
		return new SeasonDaoImpl();
	}


	
	

}

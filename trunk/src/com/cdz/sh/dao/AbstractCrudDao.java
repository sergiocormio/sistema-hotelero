package com.cdz.sh.dao;

import org.tmatesoft.sqljet.core.table.SqlJetDb;

import com.cdz.sh.dao.manager.DBManager;

public abstract class AbstractCrudDao<T> implements ICrudDao<T> {
	
	protected SqlJetDb db;
	
	public AbstractCrudDao(){
		db = DBManager.getDataBase();
	}

}

package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;

public abstract class AbstractCrudService<Entity, Id extends Serializable> implements CrudService<Entity, Id> {

	/**
	 * This dao must be created on every concrete class, depending on the Entity type
	 */
	protected CrudDao<Entity, Id> crudDao;
	
		
	@Override
	public void createRecord(Entity e) throws DaoException {
		this.crudDao.createRecord(e);
	}


	@Override
	public void updateRecord(Entity e) throws DaoException {
		this.updateRecord(e);
	}


	@Override
	public void deleteRecord(Entity e) throws DaoException {
		this.deleteRecord(e);
	}


	@Override
	public Entity getRecordById(Id id) throws DaoException {
		return this.crudDao.getRecordById(id);
	}


	@Override
	public Collection<Entity> retrieveAll() throws DaoException {
		return this.crudDao.retrieveAll();
	}
		
	
}

package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.service.exception.InvalidOperationException;

public abstract class AbstractCrudService<Entity, Id extends Serializable> implements CrudService<Entity, Id> {

	/**
	 * This dao must be created on every concrete class, depending on the Entity type
	 */
	protected CrudDao<Entity, Id> crudDao;
	
	protected AbstractCrudService(){
		this.crudDao = createDao();
	}
		
	/**
	 * to be implemented on each specific dao
	 * 
	 * @return
	 */
	protected abstract CrudDao<Entity, Id> createDao();

	
	@Override
	public Entity createRecord(Entity e) throws DaoException, InvalidOperationException {
		Entity createdEntity = this.crudDao.createRecord(e);
		return createdEntity;
	}


	@Override
	public void updateRecord(Entity e) throws DaoException {
		this.crudDao.updateRecord(e);
	}


	@Override
	public void deleteRecord(Entity e) throws DaoException {
		this.crudDao.deleteRecord(e);
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

package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.crud.CrudDao;

public abstract class AbstractCrudService<Entity, Id extends Serializable> implements CrudService<Entity, Id> {

	/**
	 * This dao must be created on every concrete class, depending on the Entity type
	 */
	protected CrudDao<Entity, Id> crudDao;
	
		
	@Override
	public void createRecord(Entity e) {
		this.crudDao.createRecord(e);
	}


	@Override
	public void updateRecord(Entity e) {
		this.updateRecord(e);
	}


	@Override
	public void deleteRecord(Entity e) {
		this.deleteRecord(e);
	}


	@Override
	public Entity getRecordById(Id id) {
		return this.crudDao.getRecordById(id);
	}


	@Override
	public Collection<Entity> retrieveAll() {
		return this.crudDao.retrieveAll();
	}
	
	
	
}

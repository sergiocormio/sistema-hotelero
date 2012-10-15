package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.service.exception.InvalidOperationException;

public interface CrudService<Entity, Id extends Serializable> {
	
	public Entity createRecord(Entity e) throws DaoException, InvalidOperationException;
	
	public void updateRecord(Entity e) throws DaoException;
	
	public void deleteRecord(Entity e) throws DaoException;
	
	public Entity getRecordById(Id id) throws DaoException;

	public Collection<Entity> retrieveAll() throws DaoException;
}

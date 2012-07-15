package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.exception.DaoException;

public interface CrudService<Entity, Id extends Serializable> {
	
	public Entity createRecord(Entity e) throws DaoException;
	
	public void updateRecord(Entity e) throws DaoException;
	
	public void deleteRecord(Entity e) throws DaoException;
	
	public Entity getRecordById(Id id) throws DaoException;

	public Collection<Entity> retrieveAll() throws DaoException;
}

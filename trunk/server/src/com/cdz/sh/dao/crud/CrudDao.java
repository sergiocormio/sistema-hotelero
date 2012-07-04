package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.util.Collection;

import com.cdz.sh.dao.exception.DaoException;

/**
 * 
 * @author fede
 *
 * @param <Entity>
 * @param <Id>
 */
public interface CrudDao<Entity, Id extends Serializable> {
	
	public void createRecord(Entity e) throws DaoException;
	
	public void updateRecord(Entity e) throws DaoException;
	
	public void deleteRecord(Entity e) throws DaoException;
	
	public Entity getRecordById(Id id) throws DaoException;
	
	public Collection<Entity> retrieveAll() throws DaoException;

}

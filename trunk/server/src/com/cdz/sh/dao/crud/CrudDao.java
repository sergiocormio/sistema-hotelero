package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author fede
 *
 * @param <Entity>
 * @param <Id>
 */
public interface CrudDao<Entity, Id extends Serializable> {
	
	public void createRecord(Entity e);
	
	public void updateRecord(Entity e);
	
	public void deleteRecord(Entity e);
	
	public Entity getRecordById(Id id);
	
	public Collection<Entity> retrieveAll();

}

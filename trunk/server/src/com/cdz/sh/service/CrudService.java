package com.cdz.sh.service;

import java.io.Serializable;
import java.util.Collection;

public interface CrudService<Entity, Id extends Serializable> {
	
public void createRecord(Entity e);
	
	public void updateRecord(Entity e);
	
	public void deleteRecord(Entity e);
	
	public Entity getRecordById(Id id);

	public Collection<Entity> retrieveAll();
}

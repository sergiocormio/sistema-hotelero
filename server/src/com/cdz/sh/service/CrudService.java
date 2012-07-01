package com.cdz.sh.service;

import java.util.Collection;

public interface CrudService<Entity, Id> {
	
public void createRecord(Entity e);
	
	public void updateRecord(Entity e);
	
	public void deleteRecord(Entity e);
	
	public Entity getRecordById(Id id);

	public Collection<Entity> retrieveAll();
}

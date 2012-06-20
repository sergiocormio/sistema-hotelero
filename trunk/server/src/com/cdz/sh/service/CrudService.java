package com.cdz.sh.service;

public interface CrudService<Entity, Id> {
	
public void createRecord(Entity e);
	
	public void updateRecord(Entity e);
	
	public void deleteRecord(Entity e);
	
	public Entity getRecordById(Id id);

}

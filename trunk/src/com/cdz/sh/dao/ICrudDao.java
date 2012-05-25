package com.cdz.sh.dao;

/**
 * 
 * @author fede
 *
 * @param <T>
 */
public interface ICrudDao<T> {
	
	public boolean createRecord(T t);
	
	public boolean updateRecord(T t);
	
	public boolean deleteRecord(T t);
	
	public T getRecord(T t);

}

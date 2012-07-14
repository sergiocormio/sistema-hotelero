package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cdz.sh.dao.exception.DaoException;



/**
 * 
 * @author fede
 *
 * @param <Entity>
 * @param <Id>
 */
public abstract class AbstractCrudDao<Entity, Id extends Serializable> implements CrudDao<Entity, Id> {

	protected Class<Entity> entityClass;

	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	protected AbstractCrudDao(){
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    this.entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
	    
	    this.entityManager = EntityManagerSingleton.getInstance();
	}
	
	@Override
	public void createRecord(Entity e) throws DaoException{
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();	
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again   
					entityManager.clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
	}

	@Override
	public void updateRecord(Entity e) throws DaoException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();	
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again
					entityManager.clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
	}

	
	@Override
	public void deleteRecord(Entity e) throws DaoException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();	
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again
					entityManager.clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
	}

	@Override
	public Entity getRecordById(Id id) throws DaoException {
		Entity entity = null;
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.find(this.entityClass, id);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();	
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
		return entity;
	}
	
	public Collection<Entity> retrieveAll() throws DaoException{
		Collection<Entity> entities = new ArrayList<Entity>();
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Entity> query = entityManager.createQuery("SELECT e FROM " + this.entityClass.getName() + " e", this.entityClass);
			entities = query.getResultList();
			
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();	
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
		return entities;
	}

}

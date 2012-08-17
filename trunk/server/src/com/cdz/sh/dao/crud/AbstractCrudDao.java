package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.lang.reflect.Method;
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

	private static final String GETTER_FOR_ID = "getId";
	protected Class<Entity> entityClass;
	protected Class<Id> idClass;

	
	@SuppressWarnings("unchecked")
	protected AbstractCrudDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    this.entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
	    this.idClass = (Class<Id>) genericSuperclass.getActualTypeArguments()[1];
	    
	    
	}
	
	@Override
	public Entity createRecord(Entity e) throws DaoException{
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			EntityManagerSingleton.getInstance().persist(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(EntityManagerSingleton.getInstance().getTransaction().getRollbackOnly()){
				EntityManagerSingleton.getInstance().getTransaction().rollback();	
			}
			else{
				try {
					EntityManagerSingleton.getInstance().getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again   
					EntityManagerSingleton.getInstance().clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
		// retrieves the entity created, with its generated id 
		return e;
	}

	@Override
	public void updateRecord(Entity e) throws DaoException {
		//check if the entity already exists
		Id entityId = extractId(e);
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			Entity entityFound = EntityManagerSingleton.getInstance().find(this.entityClass, entityId);
			if(entityFound == null){
				throw new DaoException(this.entityClass.getSimpleName(), entityId.toString());
			}
			EntityManagerSingleton.getInstance().merge(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(EntityManagerSingleton.getInstance().getTransaction().getRollbackOnly()){
				EntityManagerSingleton.getInstance().getTransaction().rollback();	
			}
			else{
				try {
					EntityManagerSingleton.getInstance().getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again
					EntityManagerSingleton.getInstance().clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Id extractId(Entity e) {
		Id entityId = null;
		try {
			Method method = e.getClass().getMethod(GETTER_FOR_ID);
			entityId = (Id)method.invoke(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return entityId;
	}

	
	@Override
	public void deleteRecord(Entity e) throws DaoException {
		//check if the entity already exists
		Id entityId = extractId(e);
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			Entity entityFound = EntityManagerSingleton.getInstance().find(this.entityClass, entityId);
			if(entityFound == null){
				throw new DaoException(this.entityClass.getSimpleName(), entityId.toString());
			}
			EntityManagerSingleton.getInstance().remove(entityFound);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(EntityManagerSingleton.getInstance().getTransaction().getRollbackOnly()){
				EntityManagerSingleton.getInstance().getTransaction().rollback();	
			}
			else{
				try {
					EntityManagerSingleton.getInstance().getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					// disassociate the object from the current session, hence it will not attempt this action again
					EntityManagerSingleton.getInstance().clear();
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
	}

	@Override
	public Entity getRecordById(Id id) throws DaoException {
						
		Entity entity = null;
		try {
			EntityManagerSingleton.getInstance().getTransaction().begin();
			entity = EntityManagerSingleton.getInstance().find(this.entityClass, id);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(EntityManagerSingleton.getInstance().getTransaction().getRollbackOnly()){
				EntityManagerSingleton.getInstance().getTransaction().rollback();	
			}
			else{
				try {
					EntityManagerSingleton.getInstance().getTransaction().commit();
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
			EntityManagerSingleton.getInstance().getTransaction().begin();
			TypedQuery<Entity> query = EntityManagerSingleton.getInstance().createQuery("SELECT e FROM " + this.entityClass.getName() + " e", this.entityClass);
			entities = query.getResultList();
			
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(EntityManagerSingleton.getInstance().getTransaction().getRollbackOnly()){
				EntityManagerSingleton.getInstance().getTransaction().rollback();	
			}
			else{
				try {
					EntityManagerSingleton.getInstance().getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					throw new DaoException(persistenceException.getMessage());
				}
			}
		}
		return entities;
	}

}

package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	public synchronized Entity createRecord(Entity e) throws DaoException{
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
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
				entityManager.close();
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
				finally{
					entityManager.close();
				}
			}
		}
		// retrieves the entity created, with its generated id 
		return e;
	}

	@Override
	public synchronized void updateRecord(Entity e) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//check if the entity already exists
		Id entityId = extractId(e);
		try {
			entityManager.getTransaction().begin();
			Entity entityFound = entityManager.find(this.entityClass, entityId);
			if(entityFound == null){
				throw new DaoException(this.entityClass.getSimpleName(), entityId.toString());
			}
			entityManager.merge(e);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();
				entityManager.close();
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
				finally{
					entityManager.close();
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
	public synchronized void deleteRecord(Entity e) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//check if the entity already exists
		Id entityId = extractId(e);
		try {
			
			entityManager.getTransaction().begin();
			Entity entityFound = entityManager.find(this.entityClass, entityId);
			if(entityFound == null){
				throw new DaoException(this.entityClass.getSimpleName(), entityId.toString());
			}
			entityManager.remove(entityFound);
		}
		catch(PersistenceException persistenceException){
			throw new DaoException(persistenceException.getMessage());
		}
		finally{
			if(entityManager.getTransaction().getRollbackOnly()){
				entityManager.getTransaction().rollback();
				entityManager.close();
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
				finally{
					entityManager.close();
				}
			}
		}
	}

	@Override
	public synchronized Entity getRecordById(Id id) throws DaoException {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
								
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
				entityManager.close();
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					throw new DaoException(persistenceException.getMessage());
				}
				finally{
					entityManager.close();
				}
			}
		}
		return entity;
	}
	
	@Override
	public synchronized Collection<Entity> retrieveAll() throws DaoException{
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
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
				entityManager.close();
			}
			else{
				try {
					entityManager.getTransaction().commit();
				}
				catch(PersistenceException persistenceException){
					throw new DaoException(persistenceException.getMessage());
				}
				finally{
					entityManager.close();
				}
			}
		}
		return entities;
	}

}

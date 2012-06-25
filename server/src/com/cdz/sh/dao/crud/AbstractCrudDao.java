package com.cdz.sh.dao.crud;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;


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
	public void createRecord(Entity e) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(e);
		
        entityManager.getTransaction().commit();
	}

	@Override
	public void updateRecord(Entity e) {
		entityManager.getTransaction().begin();
		
		entityManager.merge(e);
		
        entityManager.getTransaction().commit();
	}

	@Override
	public void deleteRecord(Entity e) {
		entityManager.getTransaction().begin();
		
		entityManager.remove(e);
		
        entityManager.getTransaction().commit();
	}

	@Override
	public Entity getRecordById(Id id) {
		entityManager.getTransaction().begin();
		
		Entity entity = entityManager.find(this.entityClass, id);
		
        entityManager.getTransaction().commit();
	    
        return entity;
	}

}

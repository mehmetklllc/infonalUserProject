package com.infonal.db;

import com.infonal.logging.MyLogger;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author mkilic
 *
 */
public class BaseRepository<E> implements IBaseRepository<E> {

    private static final String SELECT = "select u from %entityName u where %where=%entityId";
    private static final String SELECTALL = "select u from %s u";
    private final EntityManager entityManager;
    private final Class<E> entityClass;

    public BaseRepository(String persistenceUnitName, Class<E> entityClass) {
        this.entityClass = entityClass;
        entityManager = MyEntityManager.createEntityManager(persistenceUnitName);
    }

    public void close() {
        entityManager.close();
    }

    @Override
    public E persist(E entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), entity.getClass());
            entity = null;
            return entity;
        }


    }

    @Override
    public E merge(E entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), entity.getClass());
            entity = null;
            return entity;
        }

    }

    @Override
    public String remove(String entityId) {
        E entity = entityManager.getReference(entityClass, entityId);
        try {
            remove(entity);
            return entityId;
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), entity.getClass());
            entityId = null;
            return entityId;
        }

    }

    private void remove(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public long find(long entityId) {
        E entity = entityManager.find(entityClass, entityId);
        try {
            entityManager.refresh(entity);
            return entityId;
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), entity.getClass());
            entityId = -1;
            return entityId;
        }


    }

    @Override
    public E getSingleEntity(String entityId, String entityName, String where) {
        E entity = null;
        try {
            String jpql = SELECT.replace("%entityName", entityName).replace("%where", where).replace("%entityId", entityId);
            Query query = entityManager.createQuery(jpql);
            entity = (E) query.getSingleResult();
            return entity;
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), entity.getClass());
            entity = null;
            return entity;

        }

    }

    @Override
    public List<E> getAllResultlist(String entityName) {
        try {
            String jpql = String.format(SELECTALL, entityName);
            Query query = entityManager.createQuery(jpql);

            return query.getResultList();
        } catch (Exception e) {
            MyLogger.myLogging(Level.WARNING, e.getMessage(), Object.class);
            return null;
        }

    }
}

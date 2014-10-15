package com.hg.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;

/**
 * JDO BaseDao class
 */
public abstract class BaseDaoJdo<T, PK extends Serializable> implements BaseDao<T, PK> {

    protected Log logger = LogFactory.getLog(getClass());

    private final Class<T> persistentClass;

    public BaseDaoJdo(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public T findById(final PK id) {
        final PersistenceManager manager = JdoManager.getSession();
        final Object o = manager.getObjectById(persistentClass, id);
        if (o == null) {
            return null;
        }
        final T bean = persistentClass.cast(o);
        return bean;
    }

    @Override
    public List<T> findByQuery(final String query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByQuery(final String query, final Pager pager) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByQuery(final String query, final Object... values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findByQuery(final String query, final Pager pager, final Object... values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        final com.google.appengine.api.datastore.Query query//
        = new com.google.appengine.api.datastore.Query(persistentClass.getSimpleName());
        return DatastoreServiceFactory.getDatastoreService().prepare(query)
                .countEntities(FetchOptions.Builder.withDefaults());
    }

    @Override
    public T save(final T transientInstance) {
        final PersistenceManager manager = JdoManager.getSession();
        try {
            return manager.makePersistent(transientInstance);
        } finally {
            manager.close();
        }
    }

    @Override
    public void delete(final T persistentInstance) {
        final PersistenceManager manager = JdoManager.getSession();
        try {
            manager.deletePersistent(persistentInstance);
        } finally {
            manager.close();
        }
    }

    @Override
    public T merge(final T detachedInstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        final PersistenceManager manager = JdoManager.getSession();
        manager.flush();
    }

    @Override
    public void initialize(final Object obj) {
        throw new UnsupportedOperationException();
    }
}

package com.hg.core.dao;

import java.util.List;

/**
 * <p>
 * The interface should exist without implement any ORM framework
 * </p>
 * <p>
 * 此interface应脱离于ORM框架技术而存在
 * </p>
 */
public interface IBaseDaoOrm {

    /**
     * <p>
     * Find in cache first, and if not found, find in db<br>
     * findById is not thread-save, use getById if you required thread-save
     * </p>
     * <p>
     * 优先从缓存中取，取不到再从DB中取<br>
     * 本函数不保证线程安全，所以需要线程安全的话，请用getById函数
     * </p>
     *
     * @see getById
     */
    <T> T findById(final Class<T> clazz, java.io.Serializable id);

    /**
     * @see findById
     */
    <T> T getById(final Class<T> clazz, java.io.Serializable id);

    <T> List<T> findByQuery(String query);

    /**
     * @param pager contain firstResult and maxResults
     */
    <T> List<T> findByQuery(final String query, final Pager pager);

    /**
     * @param values query's values
     */
    <T> List<T> findByQuery(String query, Object... values);

    /**
     * @param pager contain firstResult and maxResults
     * @param values query's values
     */
    <T> List<T> findByQuery(final String query, final Pager pager, final Object... values);

    /**
     * @param clazz POJO's class
     */
    long count(Class<? extends Object> clazz);

    <T> T save(T transientInstance);

    <T> T delete(T persistentInstance);

    <T> T merge(T detachedInstance, final Class<T> clazz);

    void flush();

    /**
     * <p>
     * Initiative loading<br>
     * Example: xxDao.initialize(Parent.getChildren())
     * </p>
     * <p>
     * 主动加载<br>
     * 例如: xxDao.initialize(Parent.getChildren())
     * </p>
     *
     * @param obj Loading object
     */
    void initialize(Object obj);
}

package com.hg.core.service;

import java.io.Serializable;

public interface BaseService<T, PK extends Serializable> {

    /** @see com.hg.core.dao.BaseDao#findById(Serializable) */
    T get(PK id);

    /** @see com.hg.core.dao.BaseDao#save(Object) */
    T save(T object);

    /** @see com.hg.core.dao.BaseDao#delete(Object) */
    void remove(T object);

    /** @see com.hg.core.dao.BaseDao#initialize(Object) */
    void initialize(final Object obj);
}

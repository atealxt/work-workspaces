package sshdemo.core.service;

import java.io.Serializable;

public interface BaseService<T, PK extends Serializable> {

    /** @see sshdemo.core.dao.BaseDao#findById(Serializable) */
    T get(PK id);

    /** @see sshdemo.core.dao.BaseDao#save(Object) */
    T save(T object);

    /** @see sshdemo.core.dao.BaseDao#delete(Object) */
    void remove(T object);

    /** @see sshdemo.core.dao.BaseDao#initialize(Object) */
    void initialize(final Object obj);

    /** @see sshdemo.core.dao.BaseDao#count() */
    long count();
}

/*
 * Interceptor接口提供了从会话(session)回调(callback)应用程序(application)的机制，
 * 这种回调机制可以允许应用程序在持久化对象被保存、更新、删除或是加载之前，检查并（或）修改其 属性。
 * example:set current date and time,or delete invalidate data
 *
 * use way1: conf = new Configuration().setInterceptor(new MyInterceptor()).configure();
 * use way2: Session session = factory.openSession(new MyInterceptor());
 */
package com.herograve.hbn.util;

import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 *
 * @author Administrator
 */
public class MyInterceptor extends EmptyInterceptor {

    int iAfterTransactionBegin = 0;

    @Override
    public void afterTransactionBegin(Transaction tx) {
        iAfterTransactionBegin++;
    }

    @Override
    public boolean onLoad(Object entity,
        Serializable id,
        Object[] state,
        String[] propertyNames,
        Type[] types) {

        System.out.println("onLoad_iAfterTransactionBegin: " + iAfterTransactionBegin);
        return false;
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        System.out.println("afterTransactionCompletion_iAfterTransactionBegin: " + iAfterTransactionBegin);
    }
}

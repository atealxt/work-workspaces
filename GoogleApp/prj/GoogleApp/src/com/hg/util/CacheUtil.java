package com.hg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class CacheUtil {

    private static Log logger = LogFactory.getLog(CacheUtil.class);

    private final static MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();

    private CacheUtil() {}

    public static <T> T get(final String key, final Class<T> clazz) {
        try {
            final Object o = memcache.get(key);
            if (o == null) {
                return null;
            }
            final T bean = clazz.cast(o);
            return bean;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(final String key) {
        try {
            final Object o = memcache.get(key);
            if (o == null) {
                return null;
            }
            return (T) o;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static void put(final String key, final Object value) {
        try {
            memcache.put(key, value);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static boolean remove(final String key) {
        try {
            return memcache.delete(key);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}

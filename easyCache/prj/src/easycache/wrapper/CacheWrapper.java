package easycache.wrapper;

import java.io.Serializable;

import org.apache.log4j.Logger;

import easycache.CacheException;
import easycache.EasyCache;
import easycache.provider.CacheProvider;

public class CacheWrapper {

    protected final Logger logger = Logger.getLogger(getClass());

    protected CacheProvider cache;

    public CacheWrapper() throws CacheException {
        this.cache = EasyCache.getInstance().getCache();
    }

    public <T> T get(final String key, final Class<T> clazz) {
        try {
            final Object o = cache.get(key);
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
    public <T> T get(final String key) {
        try {
            final Object o = cache.get(key);
            if (o == null) {
                return null;
            }
            return (T) o;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public <T extends Serializable> boolean put(final String key, final T value) {
        try {
            return cache.put(key, value);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean remove(final String key) {
        try {
            return cache.remove(key);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public String statistics() {
        try {
            return cache.statistics();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return "get statistics error: " + e.getMessage();
        }
    }
}

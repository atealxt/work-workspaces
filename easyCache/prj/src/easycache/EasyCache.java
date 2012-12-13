package easycache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import easycache.provider.CacheLevelTimer;
import easycache.provider.CacheProvider;
import easycache.utils.PropertiesUtils;

public class EasyCache {

    private final static Logger logger = Logger.getLogger(EasyCache.class);
    private final static String KEY_EASYCACHE_PROVIDERCLASS = "easycache.provider";
    private final static Map<String, CacheProvider> CACHES = new HashMap<String, CacheProvider>();
    private final static EasyCache instance = new EasyCache();
    private final ReentrantLock lock = new ReentrantLock();

    private EasyCache() {}

    public static EasyCache getInstance() {
        return instance;
    }

    public CacheProvider getCache() throws CacheException {
        return getCache(null);
    }

    public CacheProvider getCache(final String cacheName) throws CacheException {
        CacheProvider cache = CACHES.get(cacheName);
        if (cache != null) {
            return cache;
        }
        final ReentrantLock localLock = this.lock;
        localLock.lock();
        try {
            cache = createCache(cacheName);
            CACHES.put(cacheName, cache);
        } finally {
            localLock.unlock();
        }
        return cache;
    }

    private CacheProvider createCache(final String cacheName) {
        try {
            String providerClass = PropertiesUtils.getValue(KEY_EASYCACHE_PROVIDERCLASS);
            Class<?> clazz = Class.forName(providerClass);

            CacheProvider c = (CacheProvider) clazz.newInstance();
            if (cacheName == null) {
                c.buildCache();
            } else {
                ((CacheLevelTimer) c).buildCache(cacheName);
            }
            return c;
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}

// PS:
// 可以结合本地缓存与服务器缓存，来减小和服务器之间的交互，达到更好的性能。
// 例如结合ehcache与memcached，ehcache取不到再去memcached取。
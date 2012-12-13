package easycache.wrapper;

import easycache.CacheException;
import easycache.EasyCache;


public class LocalCacheWrapper extends CacheWrapper {

    public LocalCacheWrapper() throws CacheException {}

    public LocalCacheWrapper(final String cacheName) throws CacheException {
        this.cache = EasyCache.getInstance().getCache(cacheName);
    }
}

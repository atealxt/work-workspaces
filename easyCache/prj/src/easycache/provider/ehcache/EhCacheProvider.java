package easycache.provider.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import easycache.provider.CacheLevelTimer;
import easycache.provider.CacheProvider;

public class EhCacheProvider implements CacheProvider, CacheLevelTimer {

    private final static CacheManager manager = new CacheManager();
    private final static String DEFAULT_CACHE_NAME = "DEFAULT_CACHE_NAME";
    private Cache cache;

    static {
        manager.addCache(DEFAULT_CACHE_NAME);
    }

    @Override
    public void buildCache() {
        buildCache(null);
    }

    @Override
    public void buildCache(final String cacheName) {
        Cache c = manager.getCache(cacheName);
        if (c == null) {
            c = manager.getCache(DEFAULT_CACHE_NAME);
        }
        cache = c;
    }

    @Override
    public Object get(final String key) {
        if (key == null) {
            return null;
        }
        final Element element = cache.get(key);
        if (element == null) {
            return null;
        } else {
            return element.getObjectValue();
        }
    }

    @Override
    public boolean put(final String key, final Object value) {
        cache.put(new Element(key, value));
        return true;
    }

    @Override
    public boolean remove(final String key) {
        return cache.remove(key);
    }

    @Override
    public String statistics() {
        return cache.getStatistics().toString();
    }
}
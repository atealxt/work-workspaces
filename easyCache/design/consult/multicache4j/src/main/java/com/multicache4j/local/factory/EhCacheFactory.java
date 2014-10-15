package com.multicache4j.local.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.multicache4j.local.cache.Cache;
import com.multicache4j.local.cache.EhCacheImpl;

import net.sf.ehcache.CacheManager;

public class EhCacheFactory implements CacheFactory {
    private static Logger log = Logger.getLogger(EhCacheFactory.class);
    private static CacheFactory _instance;
    private static Map<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    private CacheManager manager;
    

    private EhCacheFactory() {
        manager = CacheManager.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                manager.shutdown();
                log.info("EhCacheFactory instance shutdown");
            }
        });
    }
    
    public static CacheFactory getInstance() {
        if (_instance == null) {
            synchronized (EhCacheFactory.class) {
                if (_instance == null) {
                	_instance = new EhCacheFactory();
                }
            }
        }
        return _instance;
    }

    public Cache getCache(String name) {
        if (StringUtils.isEmpty(name)) {
            log.error("cache must have name!");
            return null;
        }

        Cache cache = cacheMap.get(name);
        if (cache == null) {
            if (!manager.cacheExists(name)) {
                manager.addCache(name);
            }
            net.sf.ehcache.Cache ehCache = manager.getCache(name);
            cache = new EhCacheImpl(ehCache, name);
            cacheMap.put(name, cache);
        }
        return cache;
    }

    public void removeCache(String name) {
        if (StringUtils.isEmpty(name)) {
            log.error("cache must have name!");
        }
        if (cacheMap.containsKey(name)) {
            cacheMap.remove(name);
        }

        if (manager.cacheExists(name)) {
            manager.removeCache(name);
        }
    }
    

}

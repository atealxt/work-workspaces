package com.multicache4j.local.factory;

import com.multicache4j.local.cache.Cache;

public interface CacheFactory {
    public Cache getCache(String name);
    public void removeCache(String name);
}

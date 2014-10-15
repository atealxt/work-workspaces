package com.multicache4j;

import com.multicache4j.local.LocalCacheFactory;
import com.multicache4j.remote.RemoteCacheFactory;

public final class MultiCacheFactory {
	//private static Logger log = Logger.getLogger(MultiCacheFactory.class);
	
    private static MultiCacheFactory _instance;
    private RemoteCacheFactory _remoteInstance;
    private LocalCacheFactory _localInstance;

    private MultiCacheFactory() {
    	_remoteInstance = RemoteCacheFactory.getInstance();
    	_localInstance = LocalCacheFactory.getInstance();
    }

    public static MultiCacheFactory getInstance() {
        if (_instance == null) {
            synchronized (MultiCacheFactory.class) {
                if (_instance == null) {
                	_instance = new MultiCacheFactory();
                }
            }
        }
        return _instance;
    }
       
    public void set(String key, String value) {
    	_localInstance.set(key, value);
    	_remoteInstance.set(key, value);
    }
    
    public String get(String key) {
    	String value = _localInstance.get(key);
    	if (value == null)
    		value = _remoteInstance.get(key);
    	return value;
    }
}

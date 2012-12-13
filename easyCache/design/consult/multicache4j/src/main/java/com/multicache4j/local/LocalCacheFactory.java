package com.multicache4j.local;

import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.multicache4j.config.ConfigReader;
import com.multicache4j.local.cache.Cache;
import com.multicache4j.local.factory.CacheFactory;
import com.multicache4j.local.factory.EhCacheFactory;

public final class LocalCacheFactory {
	private static Logger log = Logger.getLogger(LocalCacheFactory.class);
    private static LocalCacheFactory _instance;
    private ConfigReader configReader;

    private LocalCacheFactory() {
    	configReader = ConfigReader.getInstance();
    }

    public static LocalCacheFactory getInstance() {
        if (_instance == null) {
            synchronized (LocalCacheFactory.class) {
                if (_instance == null) {
                	_instance = new LocalCacheFactory();
                }
            }
        }
        return _instance;
    }
    
    // 根据key，匹配pattern，获得对应的数据源名name
    private String getLocalCacheName(String key) {
    	Iterator<String> it = configReader.getKeyMap().keySet().iterator();
    	while (it.hasNext()) {
    		String pattern = it.next();
    		if(key.matches(pattern)) {
    			String localSource = configReader.getKeyMap().get(pattern).getLocalcache();
    			return localSource;
    		}
    	}
    	return configReader.getLocalSourceDefault();
    }

    // 取得一个客户端连接
	private Cache getCache(String key) throws Exception {
		if (StringUtils.isEmpty(key)) {
			log.warn("key=" + key + "不能为空");
			return null;
		} else {
			String localCacheName = getLocalCacheName(key);
			if (StringUtils.isBlank(localCacheName)) {//该key没有匹配的池，不cache
				log.warn("key=" + key + "没有匹配的localcache");
				return null;
			}
			
			CacheFactory cacheFactory = EhCacheFactory.getInstance();
			Cache cache = cacheFactory.getCache(localCacheName);
			return cache;
		}
    }
    
    public void set(String key, String value) {
    	try {
    		Cache cache = LocalCacheFactory.getInstance().getCache(key);
   			if(cache != null) {
   				log.info("set " + key + "=" + value + " ==> " + cache);
   	   			cache.set(key, value);
   			}
   		} catch (Exception e) {
   			log.error("set error: " + e.getMessage());
   			e.printStackTrace();
   		}
    }
    
    public String get(String key) {
    	String value = null;
    	try {
    		Cache cache = LocalCacheFactory.getInstance().getCache(key);
    		if(cache != null) {
	   			value = (String)cache.get(key);
	   			log.info("get " + key + "=" + value + " <== " + cache);
    		}
   		} catch (Exception e) {
   			log.error("get error: " + e.getMessage());
   			e.printStackTrace();
   		}
   		return value;
    }
    
    public static void sleep(long time) {
		try {
        	Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
    public static void main(String[] args) {
    	LocalCacheFactory local = LocalCacheFactory.getInstance();
    	String key = "fooa";
    	String value = "abc";
    	local.set(key, value);
    	local.set("1"+key, value);
    	//local.set("2"+key, value);
    	LocalCacheFactory.sleep(1000);
    	System.out.println("ehcache: " + key + "=" + local.get(key));
    }
}

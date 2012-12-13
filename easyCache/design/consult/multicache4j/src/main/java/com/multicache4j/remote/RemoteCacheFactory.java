package com.multicache4j.remote;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.multicache4j.config.ConfigReader;
import com.multicache4j.config.model.RemoteSourceItem;
import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.pool.ChannelPool;
import com.multicache4j.remote.pool.ChannelPoolImpl;

public final class RemoteCacheFactory {
	private static Logger log = Logger.getLogger(RemoteCacheFactory.class);
    private static RemoteCacheFactory _instance;
    private ConfigReader configReader;
	private Map<String, ChannelPool> poolname2sources = new ConcurrentHashMap<String, ChannelPool>();
    
    private Lock lock = new ReentrantLock(false);//source是单实例，跟xml中个数相同，所以全部加锁没有多大影响
    // 如果刚启动时服务器不通，会造成所有线程都被锁定，直到服务器启动，这种情况很少，可以忽略不计

    private RemoteCacheFactory() {
    	configReader = ConfigReader.getInstance();
    }

    public static RemoteCacheFactory getInstance() {
        if (_instance == null) {
            synchronized (RemoteCacheFactory.class) {
                if (_instance == null) {
                	_instance = new RemoteCacheFactory();
                }
            }
        }
        return _instance;
    }
    
    // 根据key，匹配pattern，获得对应的数据源名name
    private String getPoolName(String key) {
    	Iterator<String> it = configReader.getKeyMap().keySet().iterator();
    	while (it.hasNext()) {
    		String pattern = it.next();
    		if(key.matches(pattern)) {
    			String remoteSource = configReader.getKeyMap().get(pattern).getRemotecache();
    			return remoteSource;
    		}
    	}
    	return null;
    }
    
    // 判断该数据源名是否存在
    private boolean isExistPoolName(String poolName) {
    	// 空值不匹配
    	if (StringUtils.isEmpty(poolName)) {
    		return false;
    	}
    	// 默认池
    	if (poolName.equals(configReader.getRemoteSourceDefault())) {
    		return true;
    	}
    	// 配置的池
    	Iterator<String> it = configReader.getRemoteSourceMap().keySet().iterator();
    	while (it.hasNext()) {
    		String remoteSource = it.next();
    		if(poolName.equals(remoteSource)) {
    			return true;
    		}
    	}
    	return false;
    }

    // 取得一个客户端连接
	private Channel getChannel(String key) throws Exception {
		if (StringUtils.isEmpty(key)) {
			log.warn("key=" + key + "不能为空");
			return null;
		} else {
			String poolName = getPoolName(key);
			if (poolName == null || !isExistPoolName(poolName)) {//该key没有匹配的池，不cache
				log.warn("key=" + key + "没有匹配的remotecache");
				return null;
			}
			
			ChannelPool poolsource = poolname2sources.get(poolName);
			// 创建池
			if (poolsource == null) {
				lock.lock();
				poolsource = poolname2sources.get(poolName);
				
				// (1) 再一次检查进入锁的线程，防止在系统启动时服务器还没开启造成的阻塞
				if (poolsource == null) {
					
					// (1.1) 第一个线程创建池
					log.info("(1.1) 第一个线程创建池：" + poolName);
					try {
						// 创建池对象
						RemoteSourceItem remoteSourceItem = configReader.getRemoteSourceMap().get(poolName);
						poolsource = new ChannelPoolImpl(remoteSourceItem);
						poolname2sources.put(poolName, poolsource);
					} catch (Exception e) {
						log.error("无法创建池：" + poolName);
						e.printStackTrace();
						return null;//抛出异常时直接返回
					} finally {
						lock.unlock();
					}
					
					// (1.2) 创建完池后获得连接
					log.debug("(1.2)  创建完池后获得连接：" + poolName);
					return poolsource.getChannel();
				} else {
					// (1.3) 进入锁的线程获得连接
					log.debug(" (1.3) 进入锁的线程获得连接：" + poolName);
					return poolsource.getChannel();
				}
			} else {
				// (2) 有池了正常获得连接
				log.debug(" (2) 有池了正常获得连接：" + poolName);
				return poolsource.getChannel();
			}
		}
    }
    
    public void set(String key, String value) {
    	try {
    		Channel channel = RemoteCacheFactory.getInstance().getChannel(key);
    		try {
   				log.info("set " + key + "=" + value + " ==> " + channel);
   				channel.set(key, value);
   			} finally {
   				channel.close();
   			}
   		} catch (Exception e) {
   			log.error("set error: " + e.getMessage());
   			e.printStackTrace();
   		}
    }
    
    public String get(String key) {
    	String value = null;
    	try {
   			Channel channel = RemoteCacheFactory.getInstance().getChannel(key);
   			try {
   				value = channel.get(key);
   				log.info("get " + key + "=" + value + " <== " + channel);
   			} finally {
   				channel.close();
   			}
   		} catch (Exception e) {
   			log.error("get error: " + e.getMessage());
   			e.printStackTrace();
   		}
   		return value;
    }
}

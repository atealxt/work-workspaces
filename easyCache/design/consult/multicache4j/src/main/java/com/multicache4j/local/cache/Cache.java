package com.multicache4j.local.cache;

import java.util.Map;

public interface Cache {

    public boolean set(java.lang.String key, java.lang.Object value) throws Exception;
    public boolean set(Map<String, Object> objectsMap) throws Exception;    
    public Object get(java.lang.String key) throws Exception;
    public Object[] get(java.lang.String key[]) throws Exception;    

    // Delete 操作会在Cache中 打上删除标记 
    public boolean delete(java.lang.String key) throws Exception;
    // remove 操作不会在Cache中 打上删除标记 remove an object from cache given cache key
    public boolean remove(java.lang.String key) throws Exception;
    public boolean isDelete(java.lang.String key) throws Exception;
    
    // 增加数据，必须保证Key存在，如果不存在发回-1;
    public long incr(String key, long inc) throws Exception;    
    // 减少数据，必须保证Key存在，如果不存在返回-1，数据降到0为止；
    public long decr(String key, long inc) throws Exception;
}

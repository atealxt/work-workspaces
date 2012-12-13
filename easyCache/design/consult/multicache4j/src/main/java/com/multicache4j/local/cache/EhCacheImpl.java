package com.multicache4j.local.cache;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;

import net.sf.ehcache.Element;

/**
 * 对EHCACHED的封装
 */
public class EhCacheImpl implements Cache {
	//private static Logger log = Logger.getLogger(LocalCacheImpl.class);
	private net.sf.ehcache.Cache ehCache;
	private String name;
	
	public EhCacheImpl(net.sf.ehcache.Cache ehCache, String name) {
		this.ehCache = ehCache;
		this.name = name;
	}

	public boolean delete(String key) throws Exception {
		return this.ehCache.remove(key);
	}

	public boolean remove(String key) throws Exception {
		return this.ehCache.remove(key);
	}

	public Object get(String key) throws Exception {
		Element element = this.ehCache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public boolean set(String key, Object value) throws Exception {
		Element element = new Element(key, value);
		this.ehCache.put(element);
		return true;
	}

	public boolean isDelete(String key) throws Exception {
		return false;
	}

	public Object[] get(String[] key) throws Exception {
		if (ArrayUtils.isEmpty(key)) {
			return null;
		}
		Object[] lresult = new Object[key.length];
		for (int i = 0; i < key.length; i++) {
			lresult[i] = this.get(key[i]);
		}
		return lresult;
	}
	public boolean set(Map<String, Object> objectsMap) throws Exception {
		Iterator<String> it = objectsMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = objectsMap.get(key);
			this.set(key, value);
		}
		return true;
	}

	@Override
	public long decr(String key, long inc) throws Exception {
		return 0;
	}

	@Override
	public long incr(String key, long inc) throws Exception {
		Object o = this.get(key);
		if (o != null && inc != 0l) {
			Long lResult = NumberUtils.toLong(o.toString(), 0l);
			lResult = lResult + inc;
			this.set(key, lResult);
			return lResult;
		}
		if (o == null) {
			return -1l;
			//this.put(key, Long.valueOf(inc));
			//return inc;
		}
		return NumberUtils.toLong(ObjectUtils.toString(o, "0"), 0l);
	}
	
	public String toString() {
		return "ehcache[name=" + name + "]";
	}
}

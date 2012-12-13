package com.hg.util;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.api.memcache.dev.LocalMemcacheService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;

import core.old.TestUtil;

public class CacheUtilTest extends TestUtil {

    @Override
    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        proxy.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY, Boolean.TRUE.toString());

        LocalMemcacheService memcacheService = (LocalMemcacheService) proxy.getService("memcache");
        memcacheService.start();
    }

    @Override
    @AfterTest
    public void tearDown() throws Exception {
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        LocalDatastoreService datastoreService = (LocalDatastoreService) proxy.getService("datastore_v3");
        datastoreService.clearProfiles();
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCache() throws Exception {
        /*
         * GAE 1.2.1
         * java.lang.NullPointerException at
         * com.google.appengine.api.memcache.MemcacheServicePb$MemcacheGetRequest.setNameSpace
         * (MemcacheServicePb.java:364) at
         * com.google.appengine.api.memcache.MemcacheServiceImpl.get(MemcacheServiceImpl.java:217) at
         * com.hg.util.CacheUtil.get(CacheUtil.java:60) at com.hg.util.CacheUtilTest.testCache(CacheUtilTest.java:35)
         */
         CacheUtil.put("key", "value");
         Assert.assertEquals(CacheUtil.get("key"), "value");
        System.out.println(ApiProxy.getCurrentEnvironment());

        Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
        cache.put("test", "testvalue");
        Assert.assertEquals(cache.get("test"), "testvalue");

    }
}

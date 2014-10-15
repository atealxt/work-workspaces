package com.hg.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import com.hg.pojo.Greeting;

import core.old.TestUtil;

public class GreetingDaoTest extends TestUtil {

    @Override
    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        proxy.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY, Boolean.TRUE.toString());
    }

    @Override
    @AfterTest
    public void tearDown() throws Exception {
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        LocalDatastoreService datastoreService = (LocalDatastoreService) proxy.getService("datastore_v3");
        datastoreService.clearProfiles();
        super.tearDown();
    }

    @DataProvider(name = "greetingDao.insert")
    public Object[][] testInsertDp() throws Exception {
        Greeting greeting = new Greeting();
        greeting.setContent(new Text("aaa"));
        Object[][] testCase = new Object[1][1];
        testCase[0][0] = greeting;
        return testCase;
    }

    @Test(dataProvider = "greetingDao.insert", groups = "greetingDao.insert")
    public void testInsert(Greeting greeting) throws Exception {

        GreetingDao greetingDao = getBean("GreetingDao");
        greetingDao.insert(greeting);
        Query query = new Query(Greeting.class.getSimpleName());
        Assert.assertEquals(DatastoreServiceFactory.getDatastoreService().prepare(query).countEntities(), 1);

        List<Greeting> greetings = greetingDao.findAll(0, 10);
        Assert.assertTrue(greetings.size() > 0);

        greetingDao.deleteById(greetings.get(0).getId());
        greetings = greetingDao.findAll(0, 10);
        Assert.assertTrue(greetings.size() == 0);

    }
}

package com.hg.dao;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import com.hg.constant.TypeConstant;
import com.hg.pojo.Article;
import com.hg.pojo.Type;
import com.hg.pojo.User;

import core.old.TestUtil;

public class TypeDaoTest extends TestUtil {
    @Override
    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        proxy.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY,
                Boolean.TRUE.toString());
    }

    @Override
    @AfterTest
    public void tearDown() throws Exception {
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        LocalDatastoreService datastoreService = (LocalDatastoreService) proxy
                .getService("datastore_v3");
        datastoreService.clearProfiles();
        super.tearDown();
    }

    @DataProvider(name = "TypeDao")
    public Object[][] testInsertDp() throws Exception {
        Type[] type = { new Type(TypeConstant.DEFAULT), new Type("Life") };
        Object[][] testCase = new Object[1][1];
        testCase[0][0] = type;
        return testCase;
    }

    @Test(dataProvider = "TypeDao", groups = "TypeDao")
    public void testInsert(Type[] types) throws Exception {

        TypeDao typeDao = getBean("TypeDao");
        for (Type t : types) {
            typeDao.insert(t);
        }
        Assert.assertEquals(typeDao.count(), types.length);
        Assert.assertNotNull(typeDao.findByName(TypeConstant.DEFAULT));

        Type duplicate = new Type(TypeConstant.DEFAULT);
        Assert.assertNull(typeDao.insert(duplicate));

        Article article = new Article("Title", new User("Atea"));
        article.setSummary("Summary");
        article.setContent("Content");
        article.setPostBySummary(false);
        article.setType(TypeConstant.DEFAULT);
        ArticleDao articleDao = getBean("ArticleDao");
        articleDao.insert(article);
        Type defaultType = typeDao.findById(types[0].getId());
        Assert.assertEquals(defaultType.getCountArticle(), 1);
        articleDao.deleteById(article.getId());
        Assert.assertEquals(defaultType.getCountArticle(), 0);

        for (Type t : types) {
            typeDao.deleteById(t.getId());
        }
        Assert.assertEquals(typeDao.count(), 1); // default cannot delete

    }
}

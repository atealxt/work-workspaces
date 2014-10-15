package com.hg.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import com.hg.constant.TypeConstant;
import com.hg.pojo.Article;
import com.hg.pojo.Comment;
import com.hg.pojo.User;

import core.old.TestUtil;

public class ArticleDaoTest extends TestUtil {

    private User usr = new User("atea");

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

    @DataProvider(name = "ArticleDp")
    public Object[][] ArticleDp() throws Exception {

        Article article = new Article("Title", usr);
        article.setSummary("Summary");
        article.setContent("Content");
        article.setPostBySummary(false);
        article.setType(TypeConstant.DEFAULT);

        // Comment cmt = new Comment("Title", "Comment", usr, article);
        Comment cmt = new Comment("Title", "Comment", new User("atea"), article);

        Object[][] testCase = new Object[1][2];
        testCase[0][0] = article;
        testCase[0][1] = cmt;
        return testCase;
    }

    @Test(dataProvider = "ArticleDp", groups = "Article")
    public void testArticle(Article article, Comment cmt) throws Exception {

        Query query = null;

        ArticleDao articleDao = getBean("ArticleDao");
        articleDao.insert(article);
        query = new Query(Article.class.getSimpleName());
        Assert.assertEquals(DatastoreServiceFactory.getDatastoreService()
                .prepare(query).countEntities(), 1);

        CommentDao commentDao = getBean("CommentDao");
        commentDao.insert(cmt);
        Assert.assertEquals(cmt.getArticle().getComments().size(), 1);
        Assert.assertEquals(cmt.getFounder().getName(), "atea");

        // update
        Article a = articleDao.findAllById(article.getId());
        a.setContent("update content!");
        a.setPostBySummary(true);

        commentDao.deleteById(cmt.getId());
        Assert.assertEquals(article.getComments().size(), 0);

        articleDao.deleteById(article.getId());
        Assert.assertEquals(articleDao.findAll(0, 10).size(), 0);

        List<Comment> la = commentDao.findLatast(11);
        Assert.assertEquals(la.size(), 0);
    }

}

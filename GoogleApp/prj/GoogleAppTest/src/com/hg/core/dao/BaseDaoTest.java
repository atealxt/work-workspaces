package com.hg.core.dao;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.hg.pojo.Article;
import com.hg.pojo.Type;

import core.TestBase;

public class BaseDaoTest extends TestBase {

    private IBaseDaoOrm dao = new BaseDaoJdo() {};

    @Test
    public void testFindById() {
        Assert.assertNull(dao.findById(Article.class, "agR0ZXN0cg0LEgRUeXBlIgNhYWEM"));
    }

    @Test
    public void testGetById() {
        Assert.assertNull(dao.getById(Article.class, "agR0ZXN0cg0LEgRUeXBlIgNhYWEM"));
    }

    @Test
    public void testFindByQueryString() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindByQueryStringPager() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindByQueryStringObjectArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindByQueryStringPagerObjectArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testCount() {
        fail("Not yet implemented");
    }

    @Test
    public void testSave() {
        Type type = new Type("abcde");
        dao.save(type);
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testMerge() {
        fail("Not yet implemented");
    }

    @Test
    public void testFlush() {
        fail("Not yet implemented");
    }

    @Test
    public void testInitialize() {
        fail("Not yet implemented");
    }

}

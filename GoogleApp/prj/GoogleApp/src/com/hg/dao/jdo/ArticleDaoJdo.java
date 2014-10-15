package com.hg.dao.jdo;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.hg.core.dao.BaseDaoJdo;
import com.hg.core.dao.JdoManager;
import com.hg.dao.ArticleDao;
import com.hg.dao.TypeDao;
import com.hg.pojo.Article;
import com.hg.pojo.Type;
import com.hg.util.DateUtil;

@Repository("ArticleDao")
public class ArticleDaoJdo extends BaseDaoJdo<Article, String> implements ArticleDao {

    @Autowired
    private TypeDao typeDao;

    public ArticleDaoJdo() {
        super(Article.class);
    }

    @Override
    public Article insert(final Article a) {
        if (a.getAlias() != null && findAll(a.getAlias(), a.getCreateTime()) != null) {
            // alias cannot duplicate in one day
            return null;
        }

        final Type t = typeDao.findByName(a.getType());
        t.setCountArticle(t.getCountArticle() + 1);

        return save(a);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findAll(final int start, final int length) {
        List<Article> articles = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Article.class);
            query.setOrdering("createTime desc");
            query.setRange(start, start + length);
            articles = (List<Article>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return articles;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findAll(final int start, final int length, final String typeName) {
        List<Article> articles = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Article.class, "type == typeParam");
            query.declareParameters("String typeParam");
            query.setOrdering("createTime desc");
            query.setRange(start, start + length);
            articles = (List<Article>) query.execute(typeName);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return articles;
    }

    @Override
    public long countByType(final String typeName) {
        final com.google.appengine.api.datastore.Query query//
        = new com.google.appengine.api.datastore.Query(Article.class.getSimpleName());
        query.addFilter("type", FilterOperator.EQUAL, typeName);
        return DatastoreServiceFactory.getDatastoreService().prepare(query)
                .countEntities(FetchOptions.Builder.withDefaults());
    }

    @Override
    public long countByTime(final Date from, final Date to) {
        final com.google.appengine.api.datastore.Query query//
        = new com.google.appengine.api.datastore.Query(Article.class.getSimpleName());
        query.addFilter("createTime", FilterOperator.GREATER_THAN_OR_EQUAL, from);
        query.addFilter("createTime", FilterOperator.LESS_THAN, to);
        return DatastoreServiceFactory.getDatastoreService().prepare(query)
                .countEntities(FetchOptions.Builder.withDefaults());
    }

    @Override
    public void deleteById(final String id) {
        final Article article = findById(id);
        final Type t = typeDao.findByName(article.getType());
        t.setCountArticle(t.getCountArticle() - 1);

        // need not manual delete - comment
        delete(article);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Article findAll(final String alias, final Date createTime) {
        List<Article> articles = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Article.class,
                                                 "alias == aliasParam && createTime >= Today && createTime < Tomorrow");
            query.declareParameters("String aliasParam, java.util.Date Today, java.util.Date Tomorrow");
            articles = (List<Article>) query.execute(alias, DateUtil.getZeroClock(createTime),
                                                     DateUtil.getTwentyFourClock(createTime));
            return articles.size() > 0 ? articles.get(0) : null;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findAllByTime(final int start, final int length, final Date from, final Date to) {
        List<Article> articles = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Article.class, "createTime >= from && createTime < to");
            query.declareParameters("java.util.Date from, java.util.Date to");
            query.setRange(start, start + length);
            query.setOrdering("createTime desc");
            articles = (List<Article>) query.execute(from, to);
            return articles;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findLatast(final int size) {
        List<Article> articles = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Article.class);
            query.setOrdering("createTime desc");
            query.setRange(0, size);
            articles = (List<Article>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return articles;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findAllByType(final String typeName) {
        final Query query = JdoManager.getSession().newQuery(Article.class, "type == typeParam");
        query.declareParameters("String typeParam");
        return (List<Article>) query.execute(typeName);
    }

}

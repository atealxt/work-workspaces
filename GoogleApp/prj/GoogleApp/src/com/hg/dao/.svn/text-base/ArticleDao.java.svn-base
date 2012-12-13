package com.hg.dao;

import java.util.Date;
import java.util.List;

import com.hg.core.dao.BaseDao;
import com.hg.pojo.Article;

public interface ArticleDao extends BaseDao<Article, String> {

    Article insert(Article g);

    List<Article> findAll(int start, int length);

    List<Article> findAll(int start, int length, String typeName);

    /**
     * @param createTime accurate to yyyyMMdd
     */
    Article findAll(String alias, Date createTime);

    List<Article> findAllByTime(int start, int length, Date from, Date to);

    List<Article> findAllByType(String typeName);

    List<Article> findLatast(int size);

    long countByType(String typeName);

    long countByTime(Date from, Date to);

    void deleteById(String id);
}

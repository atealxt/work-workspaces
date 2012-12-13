package com.hg.service;

import java.util.Date;
import java.util.List;

import com.hg.dto.CommentInfo;
import com.hg.dto.Topic;
import com.hg.pojo.Article;

public interface ArticleService {

    boolean postArticle(Topic t);

    boolean updateArticle(Topic t);

    boolean updReadCnt(String id, String ip);

    boolean addComment(CommentInfo info);

    boolean contains(Date from, Date to);

    List<Topic> getList(int pageNo, boolean manager);

    List<Topic> getList(int pageNo, boolean manager, String typeName);

    List<Topic> getList(int pageNo, Date from, Date to);

    Topic getById(String id);

    /**
     * @param createTime accurate to yyyyMMdd
     */
    Topic getByAlias(String alias, String createTime);

    List<Topic> getLatest(int size);

    Article getArticleById(String id);

    int getMaxPage(boolean manager);

    int getMaxPage(Date from, Date to);

    int getMaxPage(String typeName, boolean manager);

    void remove(String id);
}

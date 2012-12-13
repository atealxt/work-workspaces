package com.hg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.constant.PagingConstant;
import com.hg.constant.TypeConstant;
import com.hg.core.dao.BaseDao;
import com.hg.core.service.BaseServiceImpl;
import com.hg.dao.ArticleDao;
import com.hg.dao.CommentDao;
import com.hg.dao.TypeDao;
import com.hg.dto.CommentInfo;
import com.hg.dto.Topic;
import com.hg.pojo.Article;
import com.hg.pojo.Comment;
import com.hg.pojo.Type;
import com.hg.pojo.User;
import com.hg.service.ArticleService;
import com.hg.util.DateUtil;
import com.hg.util.GaeUtil;
import com.hg.util.StringUtil;

@Service("ArticleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements ArticleService {

    private static Log logger = LogFactory.getLog(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private TypeDao typeDao;

    @Override
    protected BaseDao<Article, String> getDao() {
        return articleDao;
    }

    @Override
    public boolean postArticle(final Topic t) {
        final Article article = new Article(t.getTitle(), new User(GaeUtil.getCurrentUser().getNickname(), t.getIp()));
        initArticle(article, t);
        if (StringUtil.isEmpty(t.getType())) {
            article.setType(TypeConstant.DEFAULT);
        } else {
            article.setType(t.getType());
        }
        // do not update pojo Type,do in Dao flow
        try {
            return articleDao.insert(article) != null;
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateArticle(final Topic t) {
        try {
            final Article article = get(t.getId());
            initArticle(article, t);

            // update Type
            if (StringUtil.isEmpty(t.getType())) {
                t.setType(TypeConstant.DEFAULT);
            }
            if (StringUtil.isEmpty(article.getType())) {
                // error data check
                article.setType(TypeConstant.DEFAULT);
                final Type type = typeDao.findByName(TypeConstant.DEFAULT);
                type.setCountArticle(type.getCountArticle() + 1);
            }
            if (!t.getType().equals(article.getType())) {
                final Type typeOld = typeDao.findByName(article.getType());
                typeOld.setCountArticle(typeOld.getCountArticle() - 1);
                final Type typeNew = typeDao.findByName(t.getType());
                typeNew.setCountArticle(typeNew.getCountArticle() + 1);
            }
            article.setType(t.getType());
            article.setTitle(t.getTitle());

            save(article);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }

        return true;
    }

    private void initArticle(final Article article, final Topic t) {
        article.setSummary(t.getSummary());
        article.setContent(t.getContent());
        article.setPostBySummary(t.isPostBySummary());
        if (!StringUtil.isEmpty(t.getAlias()) && StringUtil.isCharOrNum(t.getAlias())) {
            article.setAlias(t.getAlias());
        } else {
            article.setAlias(null);
        }
    }

    @Override
    public boolean updReadCnt(final String id, final String ip) {
        final Article article = get(id);
        final Set<String> set = article.getReadIP();
        if (!set.add(ip)) {
            return true;
        }
        article.setReadIP(set);
        save(article);// from GAE 1.5, this line is necessary
        return true;
    }

    @Override
    public boolean addComment(final CommentInfo info) {
        final Article article = get(info.getArticleId());
        final Comment cmt = new Comment(info.getTitle(), info.getContent(), //
                                        new User(info.getName(), info.getContact(), info.getIp()), article);
        commentDao.insert(cmt);
        return true;
    }

    @Override
    public List<Topic> getList(final int pageNo, final boolean manager) {
        return getList(pageNo, manager, null);
    }

    @Override
    public List<Topic> getList(final int pageNo, final boolean manager, final String typeName) {
        List<Article> arts = null;

        if (StringUtil.isEmpty(typeName)) {
            arts = manager //
                    ? articleDao.findAll(pageNo * PagingConstant.ARTICLE_MANAGE, PagingConstant.ARTICLE_MANAGE) //
                    : articleDao.findAll(pageNo * PagingConstant.ARTICLE_VIEW, PagingConstant.ARTICLE_VIEW);
        } else {
            arts = manager //
                    ? articleDao.findAll(pageNo * PagingConstant.ARTICLE_MANAGE, PagingConstant.ARTICLE_MANAGE,
                                         typeName) //
                    : articleDao.findAll(pageNo * PagingConstant.ARTICLE_VIEW, PagingConstant.ARTICLE_VIEW, typeName);
        }
        return convertVo(arts);
    }

    @Override
    public List<Topic> getList(final int pageNo, final Date from, final Date to) {
        final List<Article> arts = articleDao.findAllByTime(pageNo * PagingConstant.ARTICLE_VIEW,
                                                            PagingConstant.ARTICLE_VIEW, from, to);
        return convertVo(arts);
    }

    @Override
    public int getMaxPage(final boolean manager) {
        final int count = (int) articleDao.count();
        int maxP = 0;
        if (manager) {
            maxP = count / PagingConstant.ARTICLE_MANAGE;
            if (count % PagingConstant.ARTICLE_MANAGE == 0) {
                maxP--;
            }
        } else {
            maxP = count / PagingConstant.ARTICLE_VIEW;
            if (count % PagingConstant.ARTICLE_VIEW == 0) {
                maxP--;
            }
        }
        return maxP < 0 ? 0 : maxP;
    }

    @Override
    public int getMaxPage(final String typeName, final boolean manager) {
        if (StringUtil.isEmpty(typeName)) {
            return getMaxPage(manager);
        }
        final int count = (int) articleDao.countByType(typeName);
        int maxP = 0;
        if (manager) {
            maxP = count / PagingConstant.ARTICLE_MANAGE;
            if (count % PagingConstant.ARTICLE_MANAGE == 0) {
                maxP--;
            }
        } else {
            maxP = count / PagingConstant.ARTICLE_VIEW;
            if (count % PagingConstant.ARTICLE_VIEW == 0) {
                maxP--;
            }
        }
        return maxP < 0 ? 0 : maxP;
    }

    @Override
    public int getMaxPage(final Date from, final Date to) {
        final int count = (int) articleDao.countByTime(from, to);
        int maxP = count / PagingConstant.ARTICLE_VIEW;
        if (count % PagingConstant.ARTICLE_VIEW == 0) {
            maxP--;
        }
        return maxP;
    }

    @Override
    public void remove(final String id) {
        articleDao.deleteById(id);
    }

    @Override
    public Topic getById(final String id) {
        return convertVo(get(id));
    }

    @Override
    public Topic getByAlias(final String alias, final String createTime) {
        return convertVo(articleDao.findAll(alias, DateUtil.parse(createTime, "yyyyMMdd")));
    }

    @Override
    public Article getArticleById(final String id) {
        return get(id);
    }

    @Override
    public List<Topic> getLatest(final int size) {
        final List<Article> arts = articleDao.findLatast(size);
        return convertVo(arts);
    }

    private Topic convertVo(final Article art) {
        if (art == null) {
            return null;
        }
        final Topic vo = new Topic();
        BeanUtils.copyProperties(art, vo);
        vo.setCreateby(art.getFounder().getName());
        vo.setCreateTime(art.getCreateTime());
        vo.setReadCnt(art.getReadIP().size());
        vo.setCommentCnt(art.getComments().size());
        return vo;
    }

    private List<Topic> convertVo(final List<Article> arts) {
        final List<Topic> vos = new ArrayList<Topic>(arts.size());
        for (final Article a : arts) {
            vos.add(convertVo(a));
        }
        return vos;
    }

    @Override
    public boolean contains(final Date from, final Date to) {
        return articleDao.countByTime(from, to) > 0;
    }
}

package com.hg.dao.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.hg.core.dao.BaseDaoJdo;
import com.hg.core.dao.JdoManager;
import com.hg.dao.CommentDao;
import com.hg.pojo.Article;
import com.hg.pojo.Comment;

@Repository("CommentDao")
public class CommentDaoJdo extends BaseDaoJdo<Comment, String> implements CommentDao {

    public CommentDaoJdo() {
        super(Comment.class);
    }

    @Override
    public Comment insert(final Comment g) {
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Article article = manager.getObjectById(Article.class, g.getArticle().getId());
            g.setArticle(article);
            article.getComments().add(g); // important
            manager.makePersistent(g);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            manager.close();
        }
        return g;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findLatast(final int size) {
        List<Comment> comments = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Comment.class);
            query.setOrdering("createTime desc");
            query.setRange(0, size);
            comments = (List<Comment>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return comments;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findAll(final int start, final int length) {
        List<Comment> comments = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Comment.class);
            query.setOrdering("createTime desc");
            query.setRange(start, start + length);
            comments = (List<Comment>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return comments;
    }
}

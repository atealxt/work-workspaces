package com.hg.dao.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hg.constant.TypeConstant;
import com.hg.core.dao.BaseDaoJdo;
import com.hg.core.dao.JdoManager;
import com.hg.dao.ArticleDao;
import com.hg.dao.TypeDao;
import com.hg.pojo.Article;
import com.hg.pojo.Type;

@Repository("TypeDao")
public class TypeDaoJdo extends BaseDaoJdo<Type, String> implements TypeDao {

    @Autowired
    private ArticleDao articleDao;

    public TypeDaoJdo() {
        super(Type.class);
    }

    @Override
    public Type insert(final Type t) {
        final Type tTemp = findByName(t.getName().toLowerCase());
        if (tTemp != null) {
            return null;
        }
        return save(t);
    }

    @Override
    public Type findByName(final String name) {
        final Query query = JdoManager.getSession().newQuery(Type.class, "name == nameParam");
        query.declareParameters("String nameParam");
        query.setUnique(true);
        return Type.class.cast(query.execute(name));
    }

    /**
     * If has article exist under the type, set type to TypeConstant.DEFAULT.<br>
     * 如果该类型还有文章，因为有级联删除机制，所以不能直接删除.<br>
     */
    @Override
    public void deleteById(final String id) {
        final Type t = findById(id);
        if (TypeConstant.DEFAULT.equals(t.getName())) {
            logger.warn("Default type folder cannnot be removed");
            return;
        }

        final Type typeDefault = findByName(TypeConstant.DEFAULT);
        final List<Article> acts = articleDao.findAllByType(t.getName());
        for (final Article a : acts) {
            a.setType(TypeConstant.DEFAULT);
            typeDefault.setCountArticle(typeDefault.getCountArticle() + 1);
        }

        delete(findById(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Type> findAll() {
        List<Type> types = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Type.class);
            query.setOrdering("name asc");
            types = (List<Type>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return types;
    }

}

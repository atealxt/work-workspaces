package com.hg.dao.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.hg.core.dao.BaseDaoJdo;
import com.hg.core.dao.JdoManager;
import com.hg.dao.GreetingDao;
import com.hg.pojo.Greeting;

@Repository("GreetingDao")
public class GreetingDaoJdo extends BaseDaoJdo<Greeting, String> implements GreetingDao {

    public GreetingDaoJdo() {
        super(Greeting.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Greeting> findAll(final int start, final int length) {
        List<Greeting> greetings = null;
        final PersistenceManager manager = JdoManager.getSession();
        try {
            final Query query = manager.newQuery(Greeting.class);
            query.setOrdering("date desc");
            query.setRange(start, start + length);
            greetings = (List<Greeting>) query.execute();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return greetings;
    }

    @Override
    public Greeting insert(final Greeting g) {
        return save(g);
    }

    @Override
    public void deleteById(final String id) {
        delete(findById(id));
    }
}

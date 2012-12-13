package sshdemo.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import sshdemo.core.dao.BaseDaoJpa;
import sshdemo.dao.ChildDao;
import sshdemo.entity.Child;

@Repository("ChildDao")
public class ChildDaoJpa extends BaseDaoJpa<Child, Integer> implements ChildDao {

    public ChildDaoJpa() {
        super(Child.class);
    }

    @Override
    public Child findByName(final String name) {
        List<Child> list = findByQuery("from Child where name = ?", name);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

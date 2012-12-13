package sshdemo.dao;

import sshdemo.core.dao.BaseDao;
import sshdemo.entity.Child;

public interface ChildDao extends BaseDao<Child, Integer> {

    Child findByName(String name);
}

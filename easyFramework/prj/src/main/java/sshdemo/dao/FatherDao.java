package sshdemo.dao;

import sshdemo.core.dao.BaseDao;
import sshdemo.entity.Father;

public interface FatherDao extends BaseDao<Father, Integer> {

    Father findByName(String name);
}

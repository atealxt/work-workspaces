package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Group;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface GroupDao {

    /**
     * selectAll
     */
    public List<Group> selectAll();

    public Group findByName(String name);

    public Group findById(int id);

    public List<Group> findAll(int maxNum, int startNum);

    /**
     * Insert
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(Group obj);

    /**
     * Update
     * @return 0:ok 1:error 2:warning
     */
    public int Update(Group obj);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Delete(Group obj);
}

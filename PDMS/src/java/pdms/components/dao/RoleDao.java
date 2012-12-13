package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Role;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface RoleDao {

    /**
     * selectAll
     */
    public List<Role> selectAll();

    public Role findByName(String name);
}

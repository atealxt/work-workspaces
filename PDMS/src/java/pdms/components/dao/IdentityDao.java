package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Identity;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface IdentityDao {

    /**
     * selectAll
     */
    public List<Identity> selectAll();

    public Identity findByName(String name);
}

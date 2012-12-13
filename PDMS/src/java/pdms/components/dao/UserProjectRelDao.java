package pdms.components.dao;

import pdms.components.pojo.Project;
import pdms.components.pojo.User;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface UserProjectRelDao {

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int InsOrUpd(User usr, Project prj, boolean manage);

    /**
     * 作用：先删除全部，再调InsOrUpd重新添加，已达到有则更新，没有则添加，多则删除的功能。
     * @return 0:ok 1:error 2:warning
     */
    //public int delAllManager(Project prj);
    public int delAllRelation(Project prj);
}

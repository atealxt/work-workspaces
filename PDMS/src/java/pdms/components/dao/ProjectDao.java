package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Project;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface ProjectDao {

    public Project findById(int id);

    public Project findByName(String name);

    /**
     * 取得用户所属专区
     * @param loginId 用户ID
     */
    public List<Project> findByUserLoginId(String loginId);

    /**
     * 取得用户可管理专区
     * @param loginId 专区负责人的用户ID
     */
    public List<Project> findByManagerLoginId(String loginId);

    /**
     * 取得所有项目(也就是除了站务服务区)
     */
    public List<Project> findProjects();

    public List<Project> findAll(int maxNum, int startNum);

    /**
     * Insert
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(Project obj);

    /**
     * Update
     * @return 0:ok 1:error 2:warning
     */
    public int Update(Project obj);

    /** 指定执行SQL */
    public List<Project> findAll(int maxNum, int startNum, String querySQL);
}

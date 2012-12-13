package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.User;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface UserDao {

    public User findById(int id);

    public User findByLoginIdAndPsw(String loginId, String psw);

    public User findByLoginId(String loginId);

    /** 返回所有用户 */
    public List<User> findAll(int maxNum, int startNum);

    /** 返回所有用户数 */
    public int CountAll();

    /** 返回所有专区负责人 */
    public List<User> findAllManager(int maxNum, int startNum);

    /** 返回所有项目成员 */
    public List<User> findAllUsrs(int maxNum, int startNum);

    /** 返回专区内所有用户 */
    public List<User> findByProjectId(int projectId);

    /** 
     * 返回专区内特定用户
     * @param canManage true:项目负责人 false:普通项目开发用户
     */
    public List<User> findByProjectId(int projectId, boolean canManage);

    /**
     * Insert
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(User obj);

    /**
     * Update
     * @return 0:ok 1:error 2:warning
     */
    public int Update(User obj);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Delete(User obj);

    /** 指定执行SQL */
    public List<User> findAll(int maxNum, int startNum, String querySQL);
}

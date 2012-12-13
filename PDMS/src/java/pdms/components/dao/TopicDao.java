package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Topic;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface TopicDao {

    //public List Select(String SQL);
    public Topic findById(String id);

    /** 
     * @param loginId 发帖人ID
     */
    public List<Topic> findByUserLoginId(String loginId, int maxNum);

    /**
     * @param topiclevel 等级(Position) 1:Position1 2:Position2
     */
    public List<Topic> findByTopicLevel(int topiclevel, int maxNum);

    /** 取得最新主题 */
    public List<Topic> findLT(int maxNum);

    public List<Topic> findLT(int maxNum, String loginId);

    /** 取得未完结主题 */
    public List<Topic> findLU(int maxNum);

    public List<Topic> findByLoginIdAndPid(String loginId, int pId, int maxNum, int startNum);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(Topic obj);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Update(Topic obj);

    /** 指定执行SQL */
    public List<Topic> findAll(int maxNum, int startNum, String querySQL);
}

package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.Reply;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface ReplyDao {

    public List<Reply> findByUserLoginId(String loginId, int maxNum);

    public List<Reply> findByTopicId(String topicId, int maxNum);

    public List<Reply> findByTopicId(String topicId, int maxNum, int startNum);

    /** 取得最新回复 */
    public List<Reply> findLR(int maxNum);

    public List<Reply> findLR(int maxNum, String loginId);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(Reply obj);

    public Reply findById(String id);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Update(Reply obj);

    /** 指定执行SQL */
    public List<Reply> findAll(int maxNum, int startNum, String querySQL);
}

package pdms.platform.service;

import java.util.List;
import pdms.components.pojo.Project;
import pdms.components.vo.A0300ProjectVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0300ProjectService {

    public Project MakeVo(String projectId) throws PdmsException;

    /**
     * 页面帖子信息VO生成
     */
    public List<A0300ProjectVo> MakeTopicVo(String pId, String loginId, int maxNum, int startNum) throws PdmsException;

    /** 取得用户可见帖子总数 */
    public int getSumCount(String pId, String loginId) throws PdmsException;
}

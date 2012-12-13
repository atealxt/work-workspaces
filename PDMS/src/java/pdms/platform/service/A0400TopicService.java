package pdms.platform.service;

import java.util.List;
import pdms.components.dto.A0400TopicDto;
import pdms.components.vo.A0400TopicVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0400TopicService {

    public String createTopic(A0400TopicDto dto) throws PdmsException;

    public String editTopic(A0400TopicDto dto, String tId, String rId) throws PdmsException;

    public String createReply(String loginId, String tId, String tContent) throws PdmsException;

    /**
     * 回贴帖信息VO生成
     */
    public List<A0400TopicVo> MakeVo(String tId, int maxNum, int startNum) throws PdmsException;

    /** 取得帖子回复数 */
    public int getSumCount(String tId) throws PdmsException;

    public String closeTopic(List<String> list, String loginId) throws PdmsException;
}

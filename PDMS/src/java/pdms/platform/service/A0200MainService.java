package pdms.platform.service;

import pdms.components.vo.A0200MainVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0200MainService {

    /**
     * 导航条信息做成
     */
    public String MakeTreeInfo(String loginId, String nodeId);

    /**
     * 页面信息VO生成
     */
    public A0200MainVo MakeVo(String loginId) throws PdmsException;
}

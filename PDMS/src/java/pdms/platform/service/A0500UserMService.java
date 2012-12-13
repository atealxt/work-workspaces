package pdms.platform.service;

import java.util.List;
import pdms.components.dto.A0500UserMDto;
import pdms.components.vo.A0500UserMVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0500UserMService {

    //public List<A0500UserMVo> MakeVo(int maxNum, int startNum) throws PdmsException;
    public List<A0500UserMVo> MakeVo(int maxNum, int startNum, String pId, String type) throws PdmsException;

    public int getSumCount(String type) throws PdmsException;

    public String getIdentities();

    public String getRoles();

    public String getGroups();

    public String update(A0500UserMDto dto);

    public String createNew(A0500UserMDto dto);

    /**
     * 注销用户
     * @param list 用户ID集合
     * @return true:注销成功； false：注销失败
     */
    public boolean delUsr(List<String> list) throws PdmsException;
}

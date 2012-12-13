package pdms.platform.service;

import java.util.List;
import pdms.components.dto.A1200GroupMDto;
import pdms.components.vo.A1200GroupMVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A1200GroupMService {

    public List<A1200GroupMVo> MakeVo(int maxNum, int startNum) throws PdmsException;

    public int getSumCount() throws PdmsException;

    public String update(A1200GroupMDto dto);

    public String createNew(A1200GroupMDto dto);

    /**
     * 删除用户组
     * @param list 用户组ID集合
     * @return true:删除成功； false：删除失败
     */
    public boolean delGrp(List<String> list) throws PdmsException;
}

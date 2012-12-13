package pdms.platform.service;

import java.util.List;
import pdms.components.dto.A0600ProjectMDto;
import pdms.components.vo.A0600ProjectMVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0600ProjectMService {

    public List<A0600ProjectMVo> MakeVo(int maxNum, int startNum) throws PdmsException;

    public int getSumCount() throws PdmsException;

    //public String getManagers(String pId);
    public String getManagers();

    public String update(A0600ProjectMDto dto);

    public String createNew(A0600ProjectMDto dto);

    public String delPrj(List<String> list) throws PdmsException;
}

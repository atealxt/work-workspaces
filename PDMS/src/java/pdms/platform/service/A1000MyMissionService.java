package pdms.platform.service;

import java.util.List;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A1000MyMissionService {

    public List<A1000MyMissionVo> MakeVo(String loginId, int maxNum, int startNum, String conditions) throws
        PdmsException;

    public int getSumCount(String loginId, String conditions) throws PdmsException;

    public String receiveMission(List<String> list) throws PdmsException;

    public String submitMission(String id, String content, String fileId) throws PdmsException;
}

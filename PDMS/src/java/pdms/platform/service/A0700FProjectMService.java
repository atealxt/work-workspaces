package pdms.platform.service;

import java.util.List;
import pdms.components.dto.A1000MyMissionDto;
import pdms.components.pojo.Project;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0700FProjectMService {

    public Project getProject(String id);

    public String getPrjUsrs(String pId);

    public boolean chgPrjAn(String id, String announcement);

    public boolean chgPrjLOGO(String id, String logo, String fileId);

    public List<A1000MyMissionVo> MakeVo(String pId, int maxNum, int startNum, String conditions) throws PdmsException;

    public int getSumCount(String pId, String conditions) throws PdmsException;

    public String addMission(String loginId, A1000MyMissionDto dto);

    public String editMission(String loginId, A1000MyMissionDto dto);

    public String distMission(String mId, String uId);

    public String inspectMission(String uId, String mId, String passFlg);

    /**
     * 任务删除
     * @param list ID集合
     * @return true:删除成功； false：删除失败
     */
    public boolean delMission(List<String> list) throws PdmsException;
}

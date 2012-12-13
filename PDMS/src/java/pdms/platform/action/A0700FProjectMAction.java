package pdms.platform.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dto.A1000MyMissionDto;
import pdms.components.pojo.Project;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0700FProjectMService;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0700FProjectMAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0700FProjectMAction.class);
    private A0700FProjectMService fprojectMService;
    private Project project;
    private String prjUsrs;
    private boolean httpPath = false;
    /** file json data */
    private List<A1000MyMissionVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() {
        String id = getParamValue("id");
        logger.info("id: " + id);

        project = fprojectMService.getProject(id);
        prjUsrs = fprojectMService.getPrjUsrs(id);

        cleanSess();
        return SUCCESS;
    }

    /** 任务信息 */
    public String missionInfo() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String pId = getParamValue("pid");
        String conditions = getParamValue("conditions");

        int start = 0;
        int limit = 10;
        if (str_start != null) {
            start = Integer.parseInt(getParamValue("start"));
        }
        if (str_limit != null) {
            limit = Integer.parseInt(getParamValue("limit"));
        }
        logger.info("start: " + start);
        logger.info("limit: " + limit);
        logger.info("pId: " + pId);
        logger.info("conditions: " + conditions);

        items = fprojectMService.MakeVo(pId, limit, start, conditions);
        results = fprojectMService.getSumCount(pId, conditions);
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /** 分配任务 */
    public String distMission() throws PdmsException, IOException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String mId = getParamValue("mId");
        String u_id = getParamValue("u_id");

        logger.info("mId: " + mId);
        logger.info("u_id: " + u_id);

        getResponse().getWriter().write(fprojectMService.distMission(mId, u_id));

        cleanSess();
        return null;
    }

    /**
     * 编辑公告
     */
    public String editAN() throws IOException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String announcement = getParamValue("t_content");
        String id = getParamValue("id");
        logger.info("id: " + id);
        logger.info("announcement: " + announcement);

        if (fprojectMService.chgPrjAn(id, announcement)) {
            getResponse().getWriter().write("{success:true}");
        } else {
            getResponse().getWriter().write("{success:false,errors:'修改项目公告失败!'}");
        }

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 编辑LOGO
     */
    public String editLG() throws IOException {

        String id = getParamValue("id");
        String logo = getParamValue("logo_url");
        String f_id = getParamValue("file_id");
        if (StringUtil.isEmpty(logo)) {
            logo = getParamValue("logo_sel");
        }

        logger.info("id: " + id);
        logger.info("logo: " + logo);
        logger.info("f_id: " + f_id);

        if (fprojectMService.chgPrjLOGO(id, logo, f_id)) {
            getResponse().getWriter().write("{success:true}");
        } else {
            getResponse().getWriter().write("{success:false,errors:'修改项目公告失败!'}");
        }

        if (!StringUtil.isEmpty(f_id) && !f_id.startsWith("http")) {
            httpPath = true;
        }

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 编辑任务
     */
    public String editMission() throws IOException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String mId = getParamValue("mId");
        String t_content = getParamValue("t_content");
        String completlimit = getParamValue("completlimit");
        String confirmlimit = getParamValue("confirmlimit");

        logger.info("mId: " + mId);
        logger.info("t_content: " + t_content);
        logger.info("completlimit: " + completlimit);
        logger.info("confirmlimit: " + confirmlimit);

        A1000MyMissionDto dto = new A1000MyMissionDto();
        dto.setMId(mId);
        dto.setT_content(t_content);
        dto.setCompletlimit(completlimit);
        dto.setConfirmlimit(confirmlimit);

        getResponse().getWriter().write(fprojectMService.editMission((String) getSession().get("loginId"), dto));

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 添加新任务
     */
    public String addMission() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String pId = getParamValue("pId");
        String t_content = getParamValue("t_content");
        String completlimit = getParamValue("completlimit");
        String confirmlimit = getParamValue("confirmlimit");

        logger.info("pId: " + pId);
        logger.info("t_content: " + t_content);
        logger.info("completlimit: " + completlimit);
        logger.info("confirmlimit: " + confirmlimit);

        A1000MyMissionDto dto = new A1000MyMissionDto();
        dto.setPId(pId);
        dto.setT_content(t_content);
        dto.setCompletlimit(completlimit);
        dto.setConfirmlimit(confirmlimit);

        getResponse().getWriter().write(fprojectMService.addMission((String) getSession().get("loginId"), dto));

        cleanSess();
        return null;
    }

    /**
     * 删除任务
     */
    public String delMission() throws PdmsException, IOException {

        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);
        boolean isSuccess = fprojectMService.delMission(idList);
        getResponse().setContentType("text/json;charset=UTF-8");
        getResponse().getWriter().write("{success:" + isSuccess + "}");

        cleanSess();
        return null;
    }

    /** 验收任务 */
    public String inspectMission() throws PdmsException, IOException {

        String mId = getParamValue("mId");
        String passFlg = getParamValue("passFlg");

        logger.info("mId: " + mId);
        logger.info("passFlg: " + passFlg);

        getResponse().setContentType("text/json;charset=UTF-8");
        getResponse().getWriter().write(fprojectMService.inspectMission((String) getSession().get("loginId"), mId, passFlg));

        cleanSess();
        return null;
    }

    /**
     * 编辑组员
     */
    public String editGroupUsr() {
        return SUCCESS;
    }

    /**
     * 添加组员
     */
    public String addGroupUsr() {
        return SUCCESS;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isHttpPath() {
        return httpPath;
    }

    public void setHttpPath(boolean httpPath) {
        this.httpPath = httpPath;
    }

    public List<A1000MyMissionVo> getItems() {
        return items;
    }

    public void setItems(List<A1000MyMissionVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public String getPrjUsrs() {
        return prjUsrs;
    }

    public void setPrjUsrs(String prjUsrs) {
        this.prjUsrs = prjUsrs;
    }

//    public A0700FProjectMService getFprojectMService() {
//        return fprojectMService;
//    }

    public void setFprojectMService(A0700FProjectMService fprojectMService) {
        this.fprojectMService = fprojectMService;
    }
}

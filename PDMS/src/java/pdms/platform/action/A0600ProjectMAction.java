package pdms.platform.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dto.A0600ProjectMDto;
import pdms.components.vo.A0600ProjectMVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0600ProjectMService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0600ProjectMAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0600ProjectMAction.class);
    private A0600ProjectMService projectMService;
    /** file json data */
    private List<A0600ProjectMVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");

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

        items = projectMService.MakeVo(limit, start);
        results = projectMService.getSumCount();
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 返回项目负责人列表
     */
    public String manList() {
        getResponse().setContentType("text/html;charset=UTF-8");

//        String pId = getParamValue("pId");
//        logger.info("pId: " + pId);

        try {
            //getResponse().getWriter().write(projectMService.getManagers(pId));
            getResponse().getWriter().write(projectMService.getManagers());
        } catch (Exception ex) {
            logger.error(ex);
        }

        cleanSess();
        return null;
    }

    /**
     * 编辑项目
     */
    public String editProject() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String id = getParamValue("id");
        String pName = getParamValue("pName");
        String pMan = getParamValue("pMan");
        String pUsrs = getParamValue("pUsrs");
        String pSd = getParamValue("pSd");
        String pEd = getParamValue("pEd");
        String status = getParamValue("status");

        logger.info(id);
        logger.info(pName);
        logger.info(pMan);
        logger.info(pUsrs);
        logger.info(pSd);
        logger.info(pEd);
        logger.info(status);

        A0600ProjectMDto dto = new A0600ProjectMDto();
        dto.setId(id);
        dto.setPEd(pEd);
        dto.setPMan(pMan);
        dto.setPUsrs(pUsrs);
        dto.setPName(pName);
        dto.setPSd(pSd);
        dto.setStatus(status);

        getResponse().getWriter().write(projectMService.update(dto));

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 添加新项目
     */
    public String addProject() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String pName = getParamValue("pName");
        String pMan = getParamValue("pMan");
        String pSd = getParamValue("pSd");
        String pEd = getParamValue("pEd");
        String status = getParamValue("status");

        logger.info(pName);
        logger.info(pMan);
        logger.info(pSd);
        logger.info(pEd);
        logger.info(status);

        A0600ProjectMDto dto = new A0600ProjectMDto();
        dto.setPEd(pEd);
        dto.setPMan(pMan);
        dto.setPName(pName);
        dto.setPSd(pSd);
        dto.setStatus(status);

        getResponse().getWriter().write(projectMService.createNew(dto));

        cleanSess();
        return null;
    }

    /**
     * 关闭项目
     */
    public String delPrj() throws IOException, PdmsException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);

        getResponse().getWriter().write(projectMService.delPrj(idList));

        cleanSess();
        return null;
    }

    public List<A0600ProjectMVo> getItems() {
        return items;
    }

    public void setItems(List<A0600ProjectMVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

//    public A0600ProjectMService getProjectMService() {
//        return projectMService;
//    }

    public void setProjectMService(A0600ProjectMService projectMService) {
        this.projectMService = projectMService;
    }
}

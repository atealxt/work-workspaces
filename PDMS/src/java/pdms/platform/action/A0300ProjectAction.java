package pdms.platform.action;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.pojo.Project;
import pdms.components.vo.A0300ProjectVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0300ProjectService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0300ProjectAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0300ProjectAction.class);
    private A0300ProjectService projectService;
    private Project projectVo;
    /** file json data */
    private List<A0300ProjectVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {
        projectVo = projectService.MakeVo(getParamValue("id"));
        cleanSess();
        return SUCCESS;
    }

    public String topicList() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String pId = getParamValue("id");
        logger.info("pId: " + pId);

        int start = 0;
        int limit = 20;
        if (str_start != null) {
            start = Integer.parseInt(getParamValue("start"));
        }
        if (str_limit != null) {
            limit = Integer.parseInt(getParamValue("limit"));
        }

        logger.info("start: " + start);
        logger.info("limit: " + limit);

        String loginId = (String) getSession().get("loginId");
        items = projectService.MakeTopicVo(pId, loginId, limit, start);
        results = projectService.getSumCount(pId, loginId);
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    public Project getProjectVo() {
        return projectVo;
    }

    public void setProjectVo(Project projectVo) {
        this.projectVo = projectVo;
    }

    public List<A0300ProjectVo> getItems() {
        return items;
    }

    public void setItems(List<A0300ProjectVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

//    public A0300ProjectService getProjectService() {
//        return projectService;
//    }

    public void setProjectService(A0300ProjectService projectService) {
        this.projectService = projectService;
    }
}

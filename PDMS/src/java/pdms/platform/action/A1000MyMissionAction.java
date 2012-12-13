package pdms.platform.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1000MyMissionService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1000MyMissionAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A1000MyMissionAction.class);
    private A1000MyMissionService mymissionService;
    /** file json data */
    private List<A1000MyMissionVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {
        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
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
        logger.info("conditions: " + conditions);

        String loginId = (String) getSession().get("loginId");
        items = mymissionService.MakeVo(loginId, limit, start, conditions);
        results = mymissionService.getSumCount(loginId, conditions);
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 提交任务
     */
    public String submitMission() throws IOException, PdmsException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String id = getParamValue("id");
        String content = getParamValue("content");
        logger.info("id: " + id);
        logger.info("content: " + content);

        String f_id = getParamValue("file_id");
        logger.info("f_id: " + f_id);

        getResponse().getWriter().write(mymissionService.submitMission(id, content, f_id));

        cleanSess();
        return null;
    }

    /**
     * 受取任务
     */
    public String receiveMission() throws IOException, PdmsException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);

        getResponse().getWriter().write(mymissionService.receiveMission(idList));

        cleanSess();
        return null;
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

//    public A1000MyMissionService getMymissionService() {
//        return mymissionService;
//    }

    public void setMymissionService(A1000MyMissionService mymissionService) {
        this.mymissionService = mymissionService;
    }
}

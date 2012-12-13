package pdms.platform.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dto.A1200GroupMDto;
import pdms.components.vo.A1200GroupMVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1200GroupMService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1200GroupMAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A1200GroupMAction.class);
    private A1200GroupMService groupMService;
    /** file json data */
    private List<A1200GroupMVo> items;
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

        items = groupMService.MakeVo(limit, start);
        results = groupMService.getSumCount();
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 编辑会员
     */
    public String editGroup() throws IOException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String id = getParamValue("id");
        String gname = getParamValue("gname");
        String gusr = getParamValue("gusr");
        String grole = getParamValue("grole");
        String giden = getParamValue("giden");

        logger.info(id);
        logger.info(gname);
        logger.info(gusr);
        logger.info(grole);
        logger.info(giden);

        A1200GroupMDto dto = new A1200GroupMDto();
        dto.setGid(id);
        dto.setGname(gname);
        dto.setGiden(giden);
        dto.setGrole(grole);
        dto.setGusr(gusr);
        getResponse().getWriter().write(groupMService.update(dto));

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 添加新用户组
     */
    public String addGroup() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String gname = getParamValue("gname");
        String gusr = getParamValue("gusr");
        String grole = getParamValue("grole");
        String giden = getParamValue("giden");

        logger.info(gname);
        logger.info(gusr);
        logger.info(grole);
        logger.info(giden);

        A1200GroupMDto dto = new A1200GroupMDto();
        dto.setGname(gname);
        dto.setGiden(giden);
        dto.setGrole(grole);
        dto.setGusr(gusr);
        getResponse().getWriter().write(groupMService.createNew(dto));

        cleanSess();
        return null;
    }

    /**
     * 删除用户组
     */
    public String delGroup() throws PdmsException, IOException {
        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);
        boolean isSuccess = groupMService.delGrp(idList);
        getResponse().setContentType("text/json;charset=UTF-8");
        getResponse().getWriter().write("{success:" + isSuccess + "}");
        cleanSess();
        return null;
    }

    public List<A1200GroupMVo> getItems() {
        return items;
    }

    public void setItems(List<A1200GroupMVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

//    public A1200GroupMService getGroupMService() {
//        return groupMService;
//    }

    public void setGroupMService(A1200GroupMService groupMService) {
        this.groupMService = groupMService;
    }
}

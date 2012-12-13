package pdms.platform.action;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dto.A0400TopicDto;
import pdms.components.vo.A0400TopicVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0400TopicService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0400TopicAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0400TopicAction.class);
    private A0400TopicService topicService;
    private File file;
    private String fileName;
    /** file json data */
    private List<A0400TopicVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String tId = getParamValue("id");
        String mode = getParamValue("mode");
        logger.info("tId: " + tId);
        logger.info("mode: " + mode);

        int start = 0;
        int limit = 10;
        if (str_start != null) {
            start = Integer.parseInt(getParamValue("start"));
        }
        if (str_limit != null) {
            limit = Integer.parseInt(getParamValue("limit"));
        }
//        if ("r".equals(mode)) {
//            //取得最新回复所用
//            start = -1;
//            results = topicService.getSumCount(tId);
//            start = (int) results - 10;
//            if (start < 0) {
//                start = 0;
//            }
//        }
        logger.info("start: " + start);
        logger.info("limit: " + limit);

        //String loginId = (String) getSession().get("loginId");
        items = topicService.MakeVo(tId, limit, start);
        results = topicService.getSumCount(tId);
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 回贴
     */
    public String addReply() {

        getResponse().setContentType("text/html;charset=UTF-8");

        String loginId = (String) getSession().get("loginId");
        String tId = getParamValue("id");
        String t_content = getParamValue("t_content");

        try {
            getResponse().getWriter().write(topicService.createReply(loginId, tId, t_content));
        } catch (Exception ex) {
            logger.error(ex);
            try {
                getResponse().getWriter().write("{success:false,errors:'回贴发生异常'}");
            } catch (IOException ex1) {
                logger.error(ex1);
            }
        }

        cleanSess();
        return null;
    }

    /**
     * 修改主题
     */
    public String editTopic() {

        getResponse().setContentType("text/html;charset=UTF-8");
        String tId = getParamValue("id");
        String rId = getParamValue("rId");
        logger.info("tId: " + tId);
        logger.info("rId: " + rId);

        A0400TopicDto dto = new A0400TopicDto();
        dto.setLoginId((String) getSession().get("loginId"));
        //dto.setPid(getParamValue("id"));
        dto.setTContent(getParamValue("t_content"));
        dto.setTLevel(getParamValue("t_level"));
        dto.setTName(getParamValue("t_name"));
        dto.setTRole(getParamValue("t_role"));
        dto.setTTitle(getParamValue("t_title"));

        try {
            getResponse().getWriter().write(topicService.editTopic(dto, tId, rId));
        } catch (Exception ex) {
            logger.error(ex);
            try {
                getResponse().getWriter().write("{success:false,errors:'编辑发生异常'}");
            } catch (IOException ex1) {
                logger.error(ex1);
            }
        }

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 发表主题
     */
    public String addTopic() {

        getResponse().setContentType("text/html;charset=UTF-8");

        A0400TopicDto dto = new A0400TopicDto();
        dto.setLoginId((String) getSession().get("loginId"));
        dto.setPid(getParamValue("pId"));
        dto.setTContent(getParamValue("t_content"));
        dto.setTLevel(getParamValue("t_level"));
        dto.setTName(getParamValue("t_name"));
        dto.setTRole(getParamValue("t_role"));
        dto.setTTitle(getParamValue("t_title"));

        try {
            getResponse().getWriter().write(topicService.createTopic(dto));
        } catch (Exception ex) {
            logger.error(ex);
            try {
                getResponse().getWriter().write("{success:false,errors:'发帖发生异常'}");
            } catch (IOException ex1) {
                logger.error(ex1);
            }
        }

        cleanSess();
        return null;
    }

    /** 关闭主题(锁帖) */
    public String closeTopic() throws IOException, PdmsException {

        getResponse().setContentType("text/html;charset=UTF-8");

        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);

        getResponse().getWriter().write(topicService.closeTopic(idList, (String) getSession().get("loginId")));

        cleanSess();
        return null;
    }

    public String getUFFileName() {
        return fileName;
    }

    public void setUFFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getUF() {
        return file;
    }

    public void setUF(File file) {
        this.file = file;
    }

    public List<A0400TopicVo> getItems() {
        return items;
    }

    public void setItems(List<A0400TopicVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

//    public A0400TopicService getTopicService() {
//        return topicService;
//    }

    public void setTopicService(A0400TopicService topicService) {
        this.topicService = topicService;
    }
}

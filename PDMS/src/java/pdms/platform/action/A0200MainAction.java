package pdms.platform.action;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.vo.A0200MainVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0200MainService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0200MainAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0200MainAction.class);
    private A0200MainService mainService;
    private A0200MainVo mainVo;

    @Override
    public void validate() {
        //登录状态验证
        if (getSession().get("loginId") == null) {
            logger.info("not login!");
            addActionError("not login!");
        }
    }

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {
        mainVo = mainService.MakeVo((String) getSession().get("loginId"));
        cleanSess();
        return SUCCESS;
    }

    /**
     * 取得导航条Tree节点
     */
    public String linkInfo() {
        getResponse().setContentType("text/html;charset=UTF-8");

        String nodeId = getParamValue("node");
        String tree = mainService.MakeTreeInfo((String) getSession().get("loginId"), nodeId);
        logger.info(tree);
        try {
            getResponse().getWriter().write(tree);
        } catch (IOException ex) {
            logger.error(ex);
        }

        cleanSess();
        return null;
    }

    /**
     * 最新主题
     */
    public String latestTopic() {
        return SUCCESS;
    }

    /**
     * 最新回复
     */
    public String latestReply() {
        return SUCCESS;
    }

    /**
     * 未完结主题
     */
    public String unFinishedTopic() {
        return SUCCESS;
    }

    public A0200MainVo getMainVo() {
        return mainVo;
    }

    public void setMainVo(A0200MainVo mainVo) {
        this.mainVo = mainVo;
    }

//    public A0200MainService getMainService() {
//        return mainService;
//    }

    public void setMainService(A0200MainService mainService) {
        this.mainService = mainService;
    }
}

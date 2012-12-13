package pdms.platform.action;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.vo.A0800UserVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0800UserService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0800UserAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0800UserAction.class);
    private A0800UserService userService;
    private A0800UserVo userVo;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {
        userVo = userService.MakeVo((String) getSession().get("loginId"));
        cleanSess();
        return SUCCESS;
    }

    /**
     * 编辑用户资料
     */
    public String editUser() throws PdmsException, IOException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String loginId = getParamValue("loginId");
        String name = getParamValue("name");
        logger.info("loginId: " + loginId);
        logger.info("name: " + name);

        if (userService.changeUsrInfo((String) getSession().get("loginId"), loginId, name)) {
            getSession().put("loginId", loginId);
            getResponse().getWriter().write("{success:true}");
        } else {
            getResponse().getWriter().write("{success:false,errors:'修改用户资料失败!'}");
        }

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 修改密码
     */
    public String chgPsw() throws PdmsException, IOException {
        getResponse().setContentType("text/html;charset=UTF-8");

        String psw_old = getParamValue("psw_o");
        String psw = getParamValue("psw_r");
        //logger.info(psw);//do not print it..

        if (userService.changePsw((String) getSession().get("loginId"), psw, psw_old)) {
            getResponse().getWriter().write("{success:true}");
        } else {
            getResponse().getWriter().write("{success:false,errors:'修改密码失败!'}");
        }

        cleanSess();
        return null;
    }

    public A0800UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(A0800UserVo userVo) {
        this.userVo = userVo;
    }

//    public A0800UserService getUserService() {
//        return userService;
//    }

    public void setUserService(A0800UserService userService) {
        this.userService = userService;
    }
}

package pdms.platform.action;

import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.platform.core.EasyAction;
import pdms.platform.service.A0100LoginService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0100LoginAction extends EasyAction {

    private A0100LoginService loginService;
    private static Log logger = LogFactory.getLog(A0100LoginAction.class);

    /**
     * welcome
     */
    @Override
    public String execute() {
        return SUCCESS;
    }

    /**
     * 登录
     */
    public String login() {

        String loginId = getParamValue("userName");
        String psw = getParamValue("password");
        getResponse().setContentType("text/html;charset=UTF-8");

        try {
            if (loginService.Login(loginId, psw)) {
                getSession().put("loginId", loginId);
                getResponse().getWriter().write("{success:true}");
            } else {
                getResponse().getWriter().write("{success:false,errors:'用户名或密码错误'}");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

        cleanSess();
        //return SUCCESS;
        return null;//因为返回ajax数据，所以return null
    }

    /**
     * 退出登录
     */
    public String logout() {

        getSession().remove("loginId");

        HttpSession sess = getRequest().getSession(false);
        if (sess != null) {
            sess.invalidate();
        }

        return SUCCESS;
    }

//    public A0100LoginService getLoginService() {
//        return loginService;
//    }

    public void setLoginService(A0100LoginService loginService) {
        this.loginService = loginService;
    }
}

package pdms.platform.core;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import pdms.platform.configeration.DaoManager;
import pdms.platform.util.RoleUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public abstract class EasyAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private static Log logger = LogFactory.getLog(EasyAction.class);

    /**
     * 1.关闭db session.<br>
     * 2.清除权限缓存
     */
    public void cleanSess() {
        //logger.info("DaoManager.closeSession()");
        DaoManager.closeSession();
        RoleUtil.clearRoles();
    }

    /**
     * 取得Session
     */
    public Map getSession() {
        if (att == null) {
            logger.info("Session map is null");
            return new HashMap(0);
        }
        return att;
    }

    /**
     * 取得Request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 取得Response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 取得Param的Value
     */
    public String getParamValue(String name) {
        return this.request.getParameter(name);
    }

    /**
     * 取得ServletContext的RealPath<br>
     * Example:<br>
     * File nF = new File(getRealPath() + "/aaa.doc");<br>
     * System.out.println(nF.getPath());<br>
     * System.out.println(nF.getAbsolutePath());<br>
     * System.out.println(file.renameTo(nF));
     */
    public String getRealPath() {
        return getRequest().getSession().getServletContext().getRealPath("/");
    }

//    /** 为二级缓存设立等待时间 */
//    public void waiting4Cache() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            logger.error(ex.getMessage());
//        }
//    }
    @Override
    public void setSession(Map att) {
        this.att = att;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}

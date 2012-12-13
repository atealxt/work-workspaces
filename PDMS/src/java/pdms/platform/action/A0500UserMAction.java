package pdms.platform.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dto.A0500UserMDto;
import pdms.components.vo.A0500UserMVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0500UserMService;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0500UserMAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0500UserMAction.class);
    private A0500UserMService userMService;
    /** file json data */
    private List<A0500UserMVo> items;
    /** 记录集 */
    private long results;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String pId = getParamValue("pId");
        String type = getParamValue("type");

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
        logger.info("type: " + type);

        items = userMService.MakeVo(limit, start, pId, type);
        if (!StringUtil.isEmpty(pId)) {
            //"返回项目内用户"没有分页，不用计算总数
            results = items.size();
        } else {
            results = userMService.getSumCount(type);
        }
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /** 角色列表 */
    public String idenList() {

        getResponse().setContentType("text/html;charset=UTF-8");
        try {
            getResponse().getWriter().write(userMService.getIdentities());
        } catch (Exception ex) {
            logger.error(ex);
        }

        cleanSess();
        return null;
    }

    /** 权限列表 */
    public String roleList() {

        getResponse().setContentType("text/html;charset=UTF-8");
        try {
            getResponse().getWriter().write(userMService.getRoles());
        } catch (Exception ex) {
            logger.error(ex);
        }

        cleanSess();
        return null;
    }

    /** 组列表 */
    public String gropList() {

        getResponse().setContentType("text/html;charset=UTF-8");
        try {
            getResponse().getWriter().write(userMService.getGroups());
        } catch (Exception ex) {
            logger.error(ex);
        }

        cleanSess();
        return null;
    }

    /**
     * 编辑会员
     */
    public String editUser() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");
        String id = getParamValue("id");
        String uid = getParamValue("uid");
        String uname = getParamValue("uname");
        String ip = getParamValue("ip");
        String iden = getParamValue("iden");
        String grop = getParamValue("grop");
        String role = getParamValue("role");
        String status = getParamValue("status");

        logger.info(id);
        logger.info(uid);
        logger.info(uname);
        logger.info(ip);
        logger.info(iden);
        logger.info(grop);
        logger.info(role);
        logger.info(status);

        A0500UserMDto dto = new A0500UserMDto();
        dto.setGrop(grop);
        dto.setId(id);
        dto.setIden(iden);
        dto.setIp(ip);
        dto.setRole(role);
        dto.setStatus(status);
        dto.setUid(uid);
        dto.setUname(uname);
        getResponse().getWriter().write(userMService.update(dto));

        cleanSess();
//        waiting4Cache();
        return null;
    }

    /**
     * 添加新会员
     */
    public String addUser() throws IOException {

        getResponse().setContentType("text/html;charset=UTF-8");
        String uid = getParamValue("uid");
        String uname = getParamValue("uname");
        String ip = getParamValue("ip");
        String iden = getParamValue("iden");
        String grop = getParamValue("grop");
        String role = getParamValue("role");
        String status = getParamValue("status");

        logger.info(uid);
        logger.info(uname);
        logger.info(ip);
        logger.info(iden);
        logger.info(grop);
        logger.info(role);
        logger.info(status);

        A0500UserMDto dto = new A0500UserMDto();
        dto.setGrop(grop);
        dto.setIden(iden);
        dto.setIp(ip);
        dto.setRole(role);
        dto.setStatus(status);
        dto.setUid(uid);
        dto.setUname(uname);
        getResponse().getWriter().write(userMService.createNew(dto));

        cleanSess();
        return null;
    }

    /**
     * 注销会员
     */
    public String delUser() throws PdmsException, IOException {
        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);
        boolean isSuccess = userMService.delUsr(idList);
        getResponse().setContentType("text/json;charset=UTF-8");
        getResponse().getWriter().write("{success:" + isSuccess + "}");

        cleanSess();
        return null;
    }

    public List<A0500UserMVo> getItems() {
        return items;
    }

    public void setItems(List<A0500UserMVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

//    public A0500UserMService getUserMService() {
//        return userMService;
//    }

    public void setUserMService(A0500UserMService userMService) {
        this.userMService = userMService;
    }
}

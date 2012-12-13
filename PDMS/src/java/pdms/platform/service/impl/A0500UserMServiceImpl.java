package pdms.platform.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.GroupDao;
import pdms.components.dao.IdentityDao;
import pdms.components.dao.RoleDao;
import pdms.components.dao.UserDao;
import pdms.components.dto.A0500UserMDto;
import pdms.components.pojo.Group;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Role;
import pdms.components.pojo.User;
import pdms.components.vo.A0500UserMVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0500UserMService;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;
import static pdms.platform.constant.UserConstant.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0500UserMServiceImpl implements A0500UserMService {

    private static Log logger = LogFactory.getLog(A0500UserMServiceImpl.class);
    private UserDao userDao;
    private IdentityDao identityDao;
    private GroupDao groupDao;
    private RoleDao roleDao;

    @Override
    public List<A0500UserMVo> MakeVo(int maxNum, int startNum, String pId, String type) throws PdmsException {

        List<A0500UserMVo> ret = new ArrayList<A0500UserMVo>();

        //List<User> user = userDao.findAll(maxNum, startNum);
        List<User> user = null;
        if ("m".equals(type)) {
            //返回项目负责人
            user = userDao.findAllManager(maxNum, startNum);
        } else if ("u".equals(type)) {
            //返回项目成员(不包括项目负责人)
            user = userDao.findAllUsrs(maxNum, startNum);
        } else if (pId != null) {
            //返回项目内所有用户
            user = userDao.findByProjectId(Integer.parseInt(pId));
        } else {
            //返回用户
            user = userDao.findAll(maxNum, startNum);
        }

        for (User u : user) {
            A0500UserMVo vo = new A0500UserMVo();

            vo.setUId(u.getId());
            vo.setUIp(u.getIp());
            vo.setULoginId(u.getLoginid());
            vo.setUName(u.getName());

            int status = u.getStatus();
            if (status == STATUS_IN) {
                vo.setUStatus(STATUS_IN_SHOW);
            } else if (status == STATUS_OUT) {
                vo.setUStatus(STATUS_OUT_SHOW);
            } else {
                vo.setUStatus(STATUS_OTHER_SHOW);
            }

            Set<Identity> identities = u.getIdentities();
            int cnt = 0;
            StringBuffer sb = new StringBuffer();
            for (Iterator i = identities.iterator(); i.hasNext();) {
                Identity identity = (Identity) i.next();
                sb.append(identity.getName());
                if (cnt++ != identities.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setUIdentity(sb.toString());

            Set<Role> roles = u.getRoles();
            cnt = 0;
            sb = new StringBuffer();
            for (Iterator i = roles.iterator(); i.hasNext();) {
                Role role = (Role) i.next();
                sb.append(role.getName());
                if (cnt++ != roles.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setURole(sb.toString());

            Set<Group> groups = u.getGroups();
            cnt = 0;
            sb = new StringBuffer();
            for (Iterator i = groups.iterator(); i.hasNext();) {
                Group group = (Group) i.next();
                sb.append(group.getName());
                if (cnt++ != groups.size() - 1) {
                    sb.append(",");
                }
            }
//            logger.info(sb.toString());
            vo.setUGroup(sb.toString());

            ret.add(vo);
        }

        return ret;
    }

    @Override
    public int getSumCount(String type) throws PdmsException {

        if ("m".equals(type)) {
            //返回项目负责人
            return userDao.findAllManager(-1, 0).size();
        } else if ("u".equals(type)) {
            //返回项目成员(不包括项目负责人)
            return userDao.findAllUsrs(-1, 0).size();
        } else {
            //返回用户
//            return userDao.findAll(-1, 0).size();
            return userDao.CountAll();
        }
    }

    @Override
    public String getIdentities() {
        StringBuffer ret = new StringBuffer("[");

        List<Identity> identities = identityDao.selectAll();
        for (Identity i : identities) {
            ret.append("['");
            ret.append(i.getName());

            if (identities.indexOf(i) != identities.size() - 1) {
                ret.append("'],");
            } else {
                ret.append("']");
            }
        }

        ret.append("]");
        return ret.toString();
    }

    @Override
    public String getRoles() {
        StringBuffer ret = new StringBuffer("[");

        List<Role> roles = roleDao.selectAll();
        for (Role r : roles) {
            ret.append("['");
            ret.append(r.getName());

            if (roles.indexOf(r) != roles.size() - 1) {
                ret.append("'],");
            } else {
                ret.append("']");
            }
        }

        ret.append("]");
        return ret.toString();
    }

    @Override
    public String getGroups() {
        StringBuffer ret = new StringBuffer("[");

        List<Group> groups = groupDao.selectAll();
        for (Group g : groups) {
            ret.append("['");
            ret.append(g.getName());

            if (groups.indexOf(g) != groups.size() - 1) {
                ret.append("'],");
            } else {
                ret.append("']");
            }
        }

        ret.append("]");
        return ret.toString();
    }

    @Override
    public String update(A0500UserMDto dto) {
        String ret = "{success:true}";

        String id = dto.getId();
        String uid = dto.getUid();
        String uname = dto.getUname();
        String ip = dto.getIp();
        String iden = dto.getIden();
        String grop = dto.getGrop();
        String role = dto.getRole();
        String status = dto.getStatus();

        User usr = userDao.findById(Integer.parseInt(id));
        if (usr == null) {
            return "{success:false,errors:'无此用户'}";
        }
        usr.setLoginid(uid);
        usr.setName(uname);
        usr.setIp(ip);

        if (STATUS_IN_SHOW.equals(status)) {
            usr.setStatus(STATUS_IN);
        } else if (STATUS_OUT_SHOW.equals(status)) {
            usr.setStatus(STATUS_OUT);
        } else if (STATUS_OTHER_SHOW.equals(status)) {
            usr.setStatus(STATUS_OTHER);
        }

        //iden
        String[] idens = iden.split(",");
        Set<Identity> identities = new HashSet<Identity>(0);
        for (String s : idens) {
            if (!s.trim().equals("")) {
                Identity i = identityDao.findByName(s);
                if (i != null) {
                    identities.add(i);
                }
            }
        }
        usr.setIdentities(identities);

        //grop
        String[] grops = grop.split(",");
        Set<Group> groups = new HashSet<Group>(0);
        for (String s : grops) {
            if (!s.trim().equals("")) {
                Group i = groupDao.findByName(s);
                if (i != null) {
                    groups.add(i);
                }
            }
        }
        usr.getGroups().clear();
        usr.setGroups(null);
        usr.setGroups(groups);

        //role
        String[] rs = role.split(",");
        Set<Role> roles = new HashSet<Role>(0);
        for (String s : rs) {
            if (!s.trim().equals("")) {
                Role i = roleDao.findByName(s);
                if (i != null) {
                    roles.add(i);
                }
            }
        }
        usr.setRoles(roles);

        int updateStatus = userDao.Update(usr);
        if (updateStatus == DB_STATUS_NG) {
            return "{success:false,errors:'更新发生错误'}";
        }
        return ret;
    }

    @Override
    public String createNew(A0500UserMDto dto) {

        String ret = "{success:true}";

        String uid = dto.getUid();
        String uname = dto.getUname();
        String ip = dto.getIp();
        String iden = dto.getIden();
        String grop = dto.getGrop();
        String role = dto.getRole();
        String status = dto.getStatus();

        User usr = new User();
        usr.setLoginid(uid);
        try {
            usr.setPassword(StringUtil.getMD5Code(uid));
        } catch (PdmsException ex) {
            logger.error(ex);
        }
        usr.setName(uname);
        usr.setIp(ip);

        if (STATUS_IN_SHOW.equals(status)) {
            usr.setStatus(STATUS_IN);
        } else if (STATUS_OUT_SHOW.equals(status)) {
            usr.setStatus(STATUS_OUT);
        } else if (STATUS_OTHER_SHOW.equals(status)) {
            usr.setStatus(STATUS_OTHER);
        }

        //iden
        String[] idens = iden.split(",");
        Set<Identity> identities = new HashSet<Identity>(0);
        for (String s : idens) {
            if (!s.trim().equals("")) {
                Identity i = identityDao.findByName(s);
                if (i != null) {
                    identities.add(i);
                }
            }
        }
        usr.setIdentities(identities);

        //grop
        String[] grops = grop.split(",");
        Set<Group> groups = new HashSet<Group>(0);
        for (String s : grops) {
            if (!s.trim().equals("")) {
                Group i = groupDao.findByName(s);
                if (i != null) {
                    groups.add(i);
                }
            }
        }
        usr.setGroups(groups);

        //role
        String[] rs = role.split(",");
        Set<Role> roles = new HashSet<Role>(0);
        for (String s : rs) {
            if (!s.trim().equals("")) {
                Role i = roleDao.findByName(s);
                if (i != null) {
                    roles.add(i);
                }
            }
        }
        usr.setRoles(roles);

        int insertStatus = userDao.Insert(usr);
        if (insertStatus == DB_STATUS_NG) {
            return "{success:false,errors:'新建用户发生错误'}";
        }

        return ret;
    }

    @Override
    public boolean delUsr(List<String> list) throws PdmsException {

        for (String i : list) {
            try {
                User usr = userDao.findById(Integer.parseInt(i));
                if (usr == null) {
                    logger.error("无此用户");
                    return false;
                }
                usr.setStatus(STATUS_OUT);
                usr.setIdentities(new HashSet<Identity>(0));
                usr.setGroups(new HashSet<Group>(0));
                usr.setRoles(new HashSet<Role>(0));

                int updateStatus = userDao.Update(usr);
                if (updateStatus == DB_STATUS_NG) {
                    logger.error("注销失败");
                    return false;
                }
            } catch (Exception ex) {
                logger.error(ex);
                return false;
            }
        }

        return true;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public IdentityDao getIdentityDao() {
        return identityDao;
    }

    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

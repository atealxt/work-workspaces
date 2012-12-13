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
import pdms.components.dto.A1200GroupMDto;
import pdms.components.pojo.Group;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Role;
import pdms.components.pojo.User;
import pdms.components.vo.A1200GroupMVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1200GroupMService;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1200GroupMServiceImpl implements A1200GroupMService {

    private static Log logger = LogFactory.getLog(A1200GroupMServiceImpl.class);
    private GroupDao groupDao;
    private IdentityDao identityDao;
    private UserDao userDao;
    private RoleDao roleDao;

    @Override
    public List<A1200GroupMVo> MakeVo(int maxNum, int startNum) throws PdmsException {

        List<A1200GroupMVo> ret = new ArrayList<A1200GroupMVo>();
        List<Group> groups = groupDao.findAll(maxNum, startNum);
        for (Group g : groups) {
            A1200GroupMVo vo = new A1200GroupMVo();
            vo.setGId(Integer.toString(g.getId()));
            vo.setGName(g.getName());

            Set<Identity> identities = g.getIdentities();
            int cnt = 0;
            StringBuffer sb = new StringBuffer();
            for (Iterator i = identities.iterator(); i.hasNext();) {
                Identity identity = (Identity) i.next();
                sb.append(identity.getName());
                if (cnt++ != identities.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setGIden(sb.toString());

            Set<Role> roles = g.getRoles();
            cnt = 0;
            sb = new StringBuffer();
            for (Iterator i = roles.iterator(); i.hasNext();) {
                Role role = (Role) i.next();
                sb.append(role.getName());
                if (cnt++ != roles.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setGRole(sb.toString());

            Set<User> usrs = g.getUsers();
            cnt = 0;
            sb = new StringBuffer();
            for (Iterator i = usrs.iterator(); i.hasNext();) {
                User u = (User) i.next();
                sb.append(u.getLoginid());
                if (cnt++ != usrs.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setGUsr(sb.toString());

            ret.add(vo);
        }

        return ret;
    }

    @Override
    public int getSumCount() throws PdmsException {
        return groupDao.findAll(-1, 0).size();
    }

    @Override
    public String update(A1200GroupMDto dto) {

        String ret = "{success:true}";

        String id = dto.getGid();
        String gname = dto.getGname();
        String gusr = dto.getGusr();
        String grole = dto.getGrole();
        String giden = dto.getGiden();

        Group group = groupDao.findById(Integer.parseInt(id));
        group.setName(gname);

        //iden
        String[] idens = giden.split(",");
        Set<Identity> identities = new HashSet<Identity>(0);
        for (String s : idens) {
            if (!s.trim().equals("")) {
                Identity i = identityDao.findByName(s);
                if (i != null) {
                    identities.add(i);
                }
            }
        }
        group.setIdentities(identities);

        //role
        String[] rs = grole.split(",");
        Set<Role> roles = new HashSet<Role>(0);
        for (String s : rs) {
            if (!s.trim().equals("")) {
                Role i = roleDao.findByName(s);
                if (i != null) {
                    roles.add(i);
                }
            }
        }
        group.setRoles(roles);

        //user
        String[] us = gusr.split(",");
        Set<User> users = new HashSet<User>(0);
        for (String s : us) {
            if (!s.trim().equals("")) {
                User u = userDao.findByLoginId(s);
                if (u != null) {
                    users.add(u);
                }
            }
        }
        group.setUsers(users);

        int updateStatus = groupDao.Update(group);
        if (updateStatus == DB_STATUS_NG) {
            return "{success:false,errors:'更新用户组发生错误'}";
        }
        return ret;
    }

    @Override
    public String createNew(A1200GroupMDto dto) {

        String ret = "{success:true}";

        String gname = dto.getGname();
        String gusr = dto.getGusr();
        String grole = dto.getGrole();
        String giden = dto.getGiden();

        Group group = new Group();
        group.setName(gname);

        //iden
        String[] idens = giden.split(",");
        Set<Identity> identities = new HashSet<Identity>(0);
        for (String s : idens) {
            if (!s.trim().equals("")) {
                Identity i = identityDao.findByName(s);
                if (i != null) {
                    identities.add(i);
                }
            }
        }
        group.setIdentities(identities);

        //role
        String[] rs = grole.split(",");
        Set<Role> roles = new HashSet<Role>(0);
        for (String s : rs) {
            if (!s.trim().equals("")) {
                Role i = roleDao.findByName(s);
                if (i != null) {
                    roles.add(i);
                }
            }
        }
        group.setRoles(roles);

        //user
        String[] us = gusr.split(",");
        Set<User> users = new HashSet<User>(0);
        for (String s : us) {
            if (!s.trim().equals("")) {
                User u = userDao.findByLoginId(s);
                if (u != null) {
                    users.add(u);
                }
            }
        }
        group.setUsers(users);

        int insertStatus = groupDao.Insert(group);
        if (insertStatus == DB_STATUS_NG) {
            return "{success:false,errors:'新建用户组发生错误'}";
        }

//        for (String s : us) {
//            if (!s.trim().equals("")) {
//                User u = userDao.findByLoginId(s);
//                u.getGroups().add(group);
//                int updateStatus = userDao.Update(u);
//                if (updateStatus == DB_STATUS_NG) {
//                    return "{success:false,errors:'新建用户组发生错误'}";
//                }
//            }
//        }

        return ret;
    }

    @Override
    public boolean delGrp(List<String> list) throws PdmsException {

        for (String i : list) {
            try {
                Group group = groupDao.findById(Integer.parseInt(i));
                if (group == null) {
                    logger.error("无此用户组");
                    return false;
                }

                int delStatus = groupDao.Delete(group);
                if (delStatus == DB_STATUS_NG) {
                    logger.error("删除失败: " + group.getId());
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

package pdms.platform.util;

import java.util.HashSet;
import java.util.Set;
import pdms.components.pojo.Group;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Role;
import pdms.components.pojo.User;

/**
 * role helper
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class RoleUtil {

    //用ThreadLocal保证每次请求只取一次全权限
    private static final ThreadLocal<Set<Role>> ROLES = new ThreadLocal();

    private RoleUtil() {
    }

    /** 取得相应权限 */
    public static String getOperate(String functionName, User user) {

        StringBuffer ret = new StringBuffer();

        //用户所有权限
        Set<Role> roles = ROLES.get();
        if (roles == null) {
            roles = new HashSet<Role>();
            //取得角色权限
            Set<Identity> identities = user.getIdentities();
            for (Identity i : identities) {
                Set<Role> r = i.getRoles();
                for (Role rTem : r) {
                    roles.add(rTem);
                }
            }
            //取得用户组权限
            Set<Group> groups = user.getGroups();
            for (Group g : groups) {
                Set<Role> r = g.getRoles();
                for (Role rTem : r) {
                    roles.add(rTem);
                }
                Set<Identity> ids = g.getIdentities();
                for (Identity i : ids) {
                    Set<Role> rs = i.getRoles();
                    for (Role rTem : rs) {
                        roles.add(rTem);
                    }
                }
            }
            //取得直接权限
            Set<Role> usr_roles = user.getRoles();
            for (Role r : usr_roles) {
                roles.add(r);
            }

            ROLES.set(roles);
        }

        for (Role r : roles) {
            if (r.getFunction().getName().equals(functionName)) {
                if (ret.indexOf("C") != -1 &&
                    ret.indexOf("R") != -1 &&
                    ret.indexOf("U") != -1 &&
                    ret.indexOf("D") != -1) {
                    return ret.toString();
                }
                ret.append(r.getOperate().getName());
            }
        }
        return ret.toString();
    }

    /** 清除权限缓存 */
    public static void clearRoles() {
        Set<Role> roles = ROLES.get();
        ROLES.set(null);

        if (roles != null) {
            roles.clear();
        }
    }
}

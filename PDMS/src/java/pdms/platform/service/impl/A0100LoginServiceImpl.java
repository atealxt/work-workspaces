package pdms.platform.service.impl;

import pdms.components.dao.UserDao;
import pdms.components.pojo.User;
import pdms.platform.constant.UserConstant;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0100LoginService;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0100LoginServiceImpl implements A0100LoginService {

    private UserDao userDao;

    @Override
    public boolean Login(String loginId, String psw) throws PdmsException {

        //给访客放开登录
        if ("guest".equals(loginId)) {
            return true;
        }

        User user = userDao.findByLoginIdAndPsw(loginId, StringUtil.getMD5Code(psw));
        return (user != null && user.getStatus() != UserConstant.STATUS_OUT);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

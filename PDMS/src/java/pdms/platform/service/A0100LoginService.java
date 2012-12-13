package pdms.platform.service;

import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0100LoginService {

    /**
     * 判断登录是否合法
     * @return true:合法 false:不合法
     */
    public boolean Login(String loginId, String psw) throws PdmsException;
}

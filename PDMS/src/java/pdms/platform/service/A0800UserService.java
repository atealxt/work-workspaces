package pdms.platform.service;

import pdms.components.vo.A0800UserVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0800UserService {

    /**
     * 页面信息VO生成
     */
    public A0800UserVo MakeVo(String loginId) throws PdmsException;

    /**
     * @return true:修改成功 false:修改失败
     */
    public boolean changePsw(String loginId, String psw,String pswOld) throws PdmsException;

    /**
     * @return true:修改成功 false:修改失败
     */
    public boolean changeUsrInfo(String oldLoginId, String loginId, String name) throws PdmsException;
}

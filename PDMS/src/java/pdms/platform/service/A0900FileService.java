package pdms.platform.service;

import java.io.File;
import java.util.List;
import pdms.components.vo.A0900FileVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A0900FileService {

    /**
     * 页面信息VO生成
     * @param mode 文件管理模式 m:管理员管理 其他:会员个人文件管理
     */
    public List<A0900FileVo> MakeVo(String loginId, int maxNum, int startNum, String mode) throws PdmsException;

    /** 取得用户上传文件数 */
    public int getSumCount(String loginId, String mode) throws PdmsException;

    /**
     * 文件删除
     * @param list 文件ID集合
     * @return true:删除成功； false：删除失败
     */
    public boolean delFile(List<String> list, String loginId) throws PdmsException;

    /**
     * 文件上传
     * @param loginId 登录ID
     * @param fName 文件名
     * @param fNameAlias 文件名(别名)
     * @param file 文件
     */
    public String createFile(String loginId, String fName, String fNameAlias, File file) throws PdmsException;

    public String getFilePathByMissionId(String mId);
}

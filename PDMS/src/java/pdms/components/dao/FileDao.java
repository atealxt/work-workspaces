package pdms.components.dao;

import java.util.List;
import pdms.components.pojo.UploadFile;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface FileDao {

    public List<UploadFile> find(int maxNum, int startNum);

    public List<UploadFile> findByUserLoginId(String loginId, int maxNum, int startNum);

    public List<UploadFile> findAllByUserLoginId(String loginId);

    public UploadFile findById(String id);

    /**
     * @return fileId 删除的文件地址
     */
    public String delFile(String fileId);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(UploadFile obj);

    /**
     * Update
     * @return 0:ok 1:error 2:warning
     */
    public int Update(UploadFile obj);
}

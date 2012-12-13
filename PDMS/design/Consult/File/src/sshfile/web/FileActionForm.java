package sshfile.web;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;

public class FileActionForm
    extends ActionForm
{
    private FormFile fileContent;
    private String remark;
    private String fileId;
    public FormFile getFileContent()
    {
        return fileContent;
    }
    public String getRemark()
    {
        return remark;
    }

    public String getFileId()
    {
        return fileId;
    }

    public void setFileContent(FormFile fileContent)
    {
        this.fileContent = fileContent;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }
}

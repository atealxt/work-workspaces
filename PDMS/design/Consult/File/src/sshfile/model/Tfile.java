package sshfile.model;

public class Tfile
{
  private String fileId;
  private String fileName;
  private byte[] fileContent;
  private String remark;
    public String getFileId()
  {
    return fileId;
  }
  public String getFileName()
  {
    return fileName;
  }

  public byte[] getFileContent()
  {
    return fileContent;
  }

    public String getRemark()
    {
        return remark;
    }

    public void setFileId(String fileId)
  {
    this.fileId = fileId;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public void setFileContent(byte[] fileContent)
  {
    this.fileContent = fileContent;
  }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}

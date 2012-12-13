package sshfile.service;

import java.io.OutputStream;
import sshfile.web.FileActionForm;
import java.util.List;

public interface FileService
{
    void save(FileActionForm fileForm);
    void write(OutputStream os,String fileId);
    String getFileName(String fileId);
    List getAllFile();
}

package sshfile.service;

import java.io.*;

import sshfile.dao.*;
import sshfile.web.*;
import sshfile.model.Tfile;
import java.util.List;

public class FileServiceImpl
    implements FileService
{
    private TfileDAO tfileDAO;
    public void save(FileActionForm fileForm)
    {
        Tfile tfile = new Tfile();
        try
        {
            tfile.setFileContent(fileForm.getFileContent().getFileData());
        }
        catch (FileNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        tfile.setFileName(fileForm.getFileContent().getFileName());
        tfile.setRemark(fileForm.getRemark());
        tfileDAO.save(tfile);
    }

    public void write(OutputStream os, String fileId)
    {
        Tfile tfile = tfileDAO.findByFildId(fileId);
        try
        {
            os.write(tfile.getFileContent());
            os.flush();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public String getFileName(String fileId)
    {
       Tfile tfile = tfileDAO.findByFildId(fileId);
       return tfile.getFileName();
    }

    public List getAllFile()
    {
        return  tfileDAO.findAll();
    }

    public void setTfileDAO(TfileDAO tfileDAO)
    {
        this.tfileDAO = tfileDAO;
    }

    public TfileDAO getTfileDAO()
    {
        return tfileDAO;
    }
}

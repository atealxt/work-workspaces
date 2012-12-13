package sshfile.dao;

import sshfile.model.Tfile;
import java.util.List;

public interface TfileDAO
{
    void save(Tfile tfile);
    Tfile findByFildId(String fileId);
    List findAll();
}

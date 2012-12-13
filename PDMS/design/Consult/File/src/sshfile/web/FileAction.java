package sshfile.web;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import sshfile.service.FileService;
import org.apache.struts.util.ModuleException;
import java.io.*;
import java.util.List;

public class FileAction
    extends DispatchAction
{
    public ActionForward upload(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    {
        FileActionForm fileForm = (FileActionForm) form;
        FileService fileService = getFileService();
        fileService.save(fileForm);
        return mapping.findForward("forward");
    }
    /**
     * 列出所有文件
     */
    public ActionForward listAllFile(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
      throws ModuleException
    {
        System.out.println("here-----listAllFile--");
        FileService fileService = getFileService();
        List fileList = fileService.getAllFile();
        if (fileList != null)
        {
            System.out.println("is not null");
        }
        else
        {
            System.out.println("is null");
        }
        request.setAttribute("fileList",fileList);
        return mapping.findForward("fileList");
    }

    public ActionForward download(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws ModuleException
    {
        FileActionForm fileForm = (FileActionForm) form;
        FileService fileService = getFileService();
        String fileName = fileService.getFileName(fileForm.getFileId());
        try
        {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition",
                               "attachment;" + " filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
            fileService.write(response.getOutputStream(), fileForm.getFileId());
        }
        catch (Exception e)
        {
            throw new ModuleException(e.getMessage());
        }
        return null;
    }

    private FileService getFileService()
    {
        ApplicationContext appContext = WebApplicationContextUtils.
            getWebApplicationContext(this.getServlet().getServletContext());
        return (FileService) appContext.getBean("fileService");
    }

}

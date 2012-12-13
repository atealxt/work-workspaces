package com.oa.struts.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.oa.hibernate.beans.Upfile;
import com.oa.hibernate.dao.UpfileDAO;
import com.oa.struts.forms.UpfileForm;
import com.oa.struts.forms.PageForm;
import com.oa.struts.util.Change;
import com.oa.struts.util.Constants;
import com.oa.struts.util.Pager;

public class UpfileAction extends BaseAction{
	protected UpfileDAO upfileDAO;

	Logger log = Logger.getLogger(this.getClass());

	public UpfileDAO getUpfileDAO() {
		return upfileDAO;
	}

	public void setUpfileDAO(UpfileDAO upfileDAO) {
		this.upfileDAO = upfileDAO;
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		
		PageForm pageForm = (PageForm)getSession(request, Constants.PAGER_UPFILE);
		
		// get pager
		Pager pager = upfileDAO.findPagerAllFile(pageForm
				.getPageSize(), pageForm.getPageNo());
		
		// set upfileList
		request.setAttribute("upfileList", pager.getResultList());
		
		
		// set pager
		request.setAttribute("pager", pager);
		
		// save session
		setSession(request, Constants.PAGER_UPFILE, pageForm);
		
		ActionForward forward = mapping.findForward(Constants.LIST_KEY);
		return (forward);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		
		PageForm pageForm = (PageForm)form;
		
		// get pager
		Pager pager = upfileDAO.findPagerAllFile(pageForm.getPageSize(), pageForm.getPageNo());
		
		// set fileList
		request.setAttribute("upfileList", pager.getResultList());
		
		// set pager
		request.setAttribute("pager", pager);
		
		// save session
		setSession(request, Constants.PAGER_UPFILE, pageForm);
		
		ActionForward forward = mapping.findForward(Constants.LIST_KEY);
		return (forward);
	}

	/*
	 * wangzhenli
	 */
	public ActionForward up(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		ActionForward forward = mapping.findForward(Constants.UP_KEY);
		return (forward);
	}
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		ActionMessages messages = new ActionMessages();
		String id = request.getParameter("id");
		
		if (id == null) {
			// id not exist, save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"file.message.view.notexist"));
			saveErrors(request, messages);
			return mapping.findForward(Constants.LIST_KEY);
		} else {
			// get object
			Upfile upfile = upfileDAO.findById(id);
			if (upfile == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"upfile.message.view.notexist"));
				saveErrors(request, messages);
				return mapping.findForward(Constants.LIST_KEY);
			}
			
			// save form object
			UpfileForm upfileForm = new UpfileForm();
			upfileForm.setId(upfile.getId().toString());
			upfileForm.setFilename(upfile.getFilename());
			upfileForm.setFileuper(upfile.getFileuper());
			upfileForm.setFilesize(upfile.getFilesize());
			upfileForm.setFileuptime(upfile.getFileuptime());
			upfileForm.setFileinfo(upfile.getFileinfo());
			
			// save in request
			request.setAttribute("upfileFormBean", upfileForm);

			return mapping.findForward(Constants.VIEW_KEY);
		}
	}
	
	public ActionForward upload(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		ActionMessages messages = new ActionMessages();
		UpfileForm fileform=(UpfileForm)form;
		
		String fileuper = getUsername(request);
		String filename=fileform.getFormfile().getFileName();
		int size = fileform.getFormfile().getFileSize();
		String filesize = Change.inttostr(size)+" ×Ö½Ú";
	    String fileuptime = Change.datetostr(new Date()); 
		String fileinfo = fileform.getFileinfo();
		String queryString = request.getQueryString();
 
		// insert object
	    Upfile upfile = new Upfile();
		upfile.setFilename(filename);
		upfile.setFileuper(fileuper);
		upfile.setFilesize(filesize);
		upfile.setFileuptime(fileuptime);
		upfile.setFileinfo(fileinfo);
		upfileDAO.upload(upfile);
			  
		uploadfile(request,fileform,fileuptime,filename,size);
		fileform.reset();
					
		// save messages
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"file.message.upload.success"));
		saveErrors(request, messages);
		  	
		// get pageform from session
	    PageForm pageForm = (PageForm)getSession(request, Constants.PAGER_UPFILE);
				
		// get pager form list page
		Pager pager = upfileDAO.findPagerAllFile(pageForm.getPageSize(), pageForm.getPageNo());
		request.setAttribute("upfileList", pager.getResultList());
		request.setAttribute("pager", pager);

		return mapping.findForward(Constants.LIST_KEY); 
	}
	
	public void uploadfile(HttpServletRequest request,UpfileForm fileform,String fileuptime,String filename,int size)
	{
		  Upfile upfile1=upfileDAO.findByUptime(fileuptime);
		  //String upfilename=upfile1.getFilename();
		  String subfilename=Change.inttostr(upfile1.getId());
          String endfilename=filename.substring(filename.lastIndexOf("."));
          String upfilename=subfilename+endfilename;
	      
	      String path=request.getRealPath("/");
	      System.out.println("request.getRealPath::"+path);
	      path=path+"myfile\\allfile\\";
	      System.out.println(path);
	      
	      try{
	         InputStream in=fileform.getFormfile().getInputStream();
	         File file=new File(path,upfilename);
	         FileOutputStream file_stream=new FileOutputStream(file);
	         byte a[]=new byte[size];
	         int read=0;
	         int allread=0;
	         while(allread<size)
	          {
	  	         read=in.read(a,allread,size);
	  	         allread+=read;
	  	         System.out.println("read:"+read);
	  	         System.out.println("allread:"+allread);
	          }
            file_stream.write(a);
            in.close();
            file_stream.close();
	      }
	      catch(Exception e){e.printStackTrace();}
	}
	
	public void download(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		ActionMessages messages = new ActionMessages();
		BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
		String id = request.getParameter("id");
		String filename=request.getParameter("filename");
        String endfilename=filename.substring(filename.lastIndexOf("."));
        String downloadfilename=id+endfilename;
		String filepath=request.getRealPath("");
		System.out.println("request.getRealPath::"+filepath);
		filepath=filepath+"\\myfile\\allfile\\";
		System.out.println(filepath);
		File file=new File(filepath,downloadfilename);
		if(!file.exists()){
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
			"file.message.download.notexist"));
		}
		
		response.setHeader("Content-disposition","attachment;filename="+downloadfilename);
		response.setContentType("application/plain");
		try{
			//bis = new BufferedInputStream(new java.io.FileInputStream(new
            //        String((filepath+filename).getBytes("iso-8859-1"), "gb2312")));
			bis = new BufferedInputStream(new java.io.FileInputStream(new
                    String(filepath+downloadfilename)));
            bos = new BufferedOutputStream(response.getOutputStream());
            int bytesRead = 0;
            byte[] buff = new byte[2048];
            while ( -1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
			"file.message.download.success"));
		}
		catch(Exception e)
		{
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		    "file.message.download.notexist"));
		}
		finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
		saveErrors(request, messages);
		//return mapping.findForward(Constants.LIST_KEY);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isTimeout(request)) {
		    return mapping.findForward(Constants.INDEX_KEY);
		}
		ActionMessages messages = new ActionMessages();
		String id = request.getParameter("id");
		String filepath=request.getRealPath("");
		filepath+="\\myfile\\allfile\\";
		Upfile upfile=upfileDAO.findById(id);
		String filename=upfile.getFilename();
		String endfilename=filename.substring(filename.lastIndexOf("."));
		File delfile=new File(filepath,id+endfilename);
		if (id == null) {
			// if id not exist
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"file.message.delete.notexist"));
		} else {
			// delete object
			upfileDAO.delete(id);
			
			if(delfile.exists())
				delfile.delete();
			else{
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"file.message.delete.notexist"));
			} 
			// save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"file.message.delete.success"));
			
			// get pageForm from session
			PageForm pageForm = (PageForm)getSession(request, Constants.PAGER_UPFILE);
			
			// get pager form list page
			Pager pager = upfileDAO.findPagerAllFile(pageForm
					.getPageSize(), pageForm.getPageNo());
			request.setAttribute("upfileList", pager.getResultList());
			request.setAttribute("pager", pager);
		}
		saveErrors(request, messages);
		return mapping.findForward(Constants.LIST_KEY);
	}

}

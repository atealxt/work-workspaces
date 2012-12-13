package com.oa.struts.actions;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DownloadAction;
import org.apache.struts.actions.DownloadAction.FileStreamInfo;
import org.apache.struts.actions.DownloadAction.StreamInfo;

import com.oa.hibernate.beans.Upfile;
import com.oa.hibernate.dao.UpfileDAO;

public class DownloadFileAction extends DownloadAction {
	
	protected UpfileDAO upfileDAO;

	Logger log = Logger.getLogger(this.getClass());

	public UpfileDAO getUpfileDAO() {
		return upfileDAO;
	}

	public void setUpfileDAO(UpfileDAO upfileDAO) {
		this.upfileDAO = upfileDAO;
	}
	
public StreamInfo getStreamInfo(ActionMapping mapping,ActionForm
	  form,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    String id = request.getParameter("id");
	Upfile upfile=upfileDAO.findById(id);
	String filename=upfile.getFilename();
	//String filename=request.getParameter("filename");
	byte b[]=filename.getBytes("utf-8");
	filename=new String(b);
	response.setHeader("Content-disposition","attachment; filename="+filename); 
	response.setCharacterEncoding("utf-8");
	File file=new File(request.getRealPath("/myfile/allfile")+"/"+filename);
		return new FileStreamInfo("",file); }
 
}

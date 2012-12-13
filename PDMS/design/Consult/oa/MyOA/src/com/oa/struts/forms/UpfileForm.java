package com.oa.struts.forms;

import java.util.Date;

import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.oa.struts.util.Constants;

public class UpfileForm extends ActionForm {

	private static final long serialVersionUID = -9064004131486821122L;

	protected String id = null;
	
	private FormFile formfile=null;

	protected String filename = null;

	protected String fileuper = null;

	protected String filesize = null;

	protected String fileinfo = null;

	protected String fileuptime = null;

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		ActionErrors errors = new ActionErrors();
		String queryString = arg1.getQueryString();
		
		if (queryString.equalsIgnoreCase("method=upload")) {
			//check filename
			String filename=this.getFormfile().getFileName();
		    if(filename==null||filename.equals("")){
		    	errors.add("nofile",new ActionMessage("file.error.nofile"));
		    }
		    if(filename.lastIndexOf(".")==-1){
		    	errors.add("filename",new ActionMessage("file.error.filename"));
		    }
		    int size=this.getFormfile().getFileSize();
		    if(size>Constants.MAXLENGTH){
		        errors.add("filesize",new ActionMessage("file.error.filesize"));
		    }
		}
		arg1.setAttribute("upfileFormBean", this);
		return errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public FormFile getFormfile() {
		return formfile;
	}

	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileuper() {
		return fileuper;
	}

	public void setFileuper(String fileuper) {
		this.fileuper = fileuper;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFileinfo() {
		return fileinfo;
	}

	public void setFileinfo(String fileinfo) {
		this.fileinfo = fileinfo;
	}

	public String getFileuptime() {
		return fileuptime;
	}

	public void setFileuptime(String fileuptime) {
		this.fileuptime = fileuptime;
	}
	
	public void reset()
	{
		this.fileinfo=null;
		this.filename=null;
		this.filesize=null;
		this.fileuper=null;
		this.fileuptime=null;
		this.formfile=null;
	}

}

package com.m2.web.action;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport{
	
	/**
	 * 
	 */
	
	private static final Log logger = LogFactory.getLog(BaseAction.class);

	
	private static final long serialVersionUID = -3985068377551006124L;
	
    private String pageTitle;
    
    private String submit;

	private String action = "index";
	
	
	
	public static final String ERROR="error";  //当有运行时未可预料的异常时，跳转到全局的出错页面。
	
	public static final String DETAIL="detail";  
	
	public static final String INDEX="index";  
	
	public static final String FORADD="forAdd";
	
	public static final String FORUPDATE="forUpdate";
	
	/**
	 * 该方法用于导航，返回子类Action的结果视图
	 * @param  method
	 * @return 
	 * @throws Exception
	 * 
	 */

	protected String executeMethod(String method) throws Exception{
		
		Method md = this.getClass().getMethod(method, null);
		
		String result = (String)md.invoke(this, null);
		
		return result;
		
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	
	public String sysError(Exception e){
		logger.error(e);
		this.addActionError(e.toString());
		return ERROR;
	}

    
	
	
	
	
	
 
}

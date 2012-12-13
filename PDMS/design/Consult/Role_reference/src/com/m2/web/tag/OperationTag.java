package com.m2.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class OperationTag extends ComponentTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625296193286995821L;

	private String info;
	
	private String action;
	
	private String type;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		
		return new Operation(arg0);
	}			
	
	protected void populateParams() {
		super.populateParams();
		
		Operation op=(Operation)component;
		op.setAction(action);
		op.setInfo(info);
		op.setType(type);

	}		

}

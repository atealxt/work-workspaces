package com.m2.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class FuncTreeTag extends ComponentTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4449101455187934436L;
	
	private String location;
	
	

	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		
		return new FuncTreeRenderer(arg0);
	}		
	
	protected void populateParams() {
		super.populateParams();
		FuncTreeRenderer tree = (FuncTreeRenderer)component;
		tree.setLocation(getLocation());
	}
	

}

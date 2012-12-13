package com.m2.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class DataGridTag extends ComponentTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7082820023644452888L;

	private String styleClass;
	
	private String binding;
	
	private String name;
	
	private String resource;
	
	
	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	
	

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		
		return new DataGridRenderer(arg0);
	}		
	
	
	

	protected void populateParams() {
		
		super.populateParams();
		DataGridRenderer d = (DataGridRenderer)component;
        d.setStyleClass(styleClass);
        d.setName(name);
        d.setResource(resource);
        
	}		
		

}

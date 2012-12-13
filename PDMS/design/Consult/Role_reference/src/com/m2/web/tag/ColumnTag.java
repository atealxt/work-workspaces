package com.m2.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class ColumnTag extends ComponentTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7806275009101849724L;
	protected String field;
	protected String header;
	protected String type;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		
		return new Column(arg0);
	}		

	protected void populateParams() {
		super.populateParams();
		Column col = (Column) component;
		col.setField(field);
		col.setHeader(header);
        col.setType(type);
	}		
	

}

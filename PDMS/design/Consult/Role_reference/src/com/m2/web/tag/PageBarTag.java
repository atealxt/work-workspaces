package com.m2.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import com.m2.common.Constant;
import com.opensymphony.xwork2.util.ValueStack;

public class PageBarTag extends ComponentTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228166030347139859L;

	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
	
		return new PageBarRenderer(arg0);
	}	
	protected String styleClass = "";
	
	protected int pageSize = Constant.PAGESIZE_DEFAULT;
	
	protected String binding;
	
	protected String submitFunction;

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	
	public String getSubmitFunction() {
		return submitFunction;
	}

	public void setSubmitFunction(String submitFunction) {
		this.submitFunction = submitFunction;
	}

	protected void populateParams() {
		super.populateParams();

		PageBarRenderer pageBar = (PageBarRenderer) component;
		
		pageBar.setStyleClass(styleClass);
		pageBar.setPageSize(pageSize);
		pageBar.setBindingValue(binding);

	}	
	
	
	
	

}

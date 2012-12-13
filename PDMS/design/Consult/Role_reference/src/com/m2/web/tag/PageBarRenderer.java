package com.m2.web.tag;

import java.io.Writer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.m2.service.PaginationService;

public class PageBarRenderer extends Component{
	
	private static final Log logger = LogFactory.getLog(PageBarRenderer.class);
	
	protected String styleClass = "";
	
	protected int pageSize;
	
	protected String bindingValue;
	
	protected String submitFunction;
	
	public String getBindingValue() {
		return bindingValue;
	}


	public void setBindingValue(String bindingValue) {
		this.bindingValue = bindingValue;
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


	public PageBarRenderer(ValueStack arg0) {
		super(arg0);
	}
	
	
	
	
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		if (bindingValue == null) {
			bindingValue = "pagination";
		} else if (altSyntax()) {
			if (bindingValue.startsWith("%{") && bindingValue.endsWith("}")) {
				bindingValue = bindingValue.substring(2, bindingValue.length() - 1);
			}
		}		
		PaginationService pagination = (PaginationService) this.getStack().findValue(bindingValue);
		pagination.setPageSize(this.pageSize);
		String pageBar = pagination.renderPageBar(getSubmitFunction(), getStyleClass());
		try{
		    writer.write(pageBar);
		}catch(Exception e){
			logger.error(e);
		}
		return result;
	}
	
	
	
}

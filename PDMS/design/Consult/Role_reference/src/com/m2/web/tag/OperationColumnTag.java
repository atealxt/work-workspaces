package com.m2.web.tag;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;


public class OperationColumnTag extends ColumnTag implements OperationAppender{
    
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3556948777475852849L;
	
	private List<Operation> operations  =new ArrayList<Operation>();
	
	private String param;
	
	public void addOperation(Operation op){
		this.operations.add(op);
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		
		return new OperationColumn(arg0);
	}		

	protected void populateParams() {
		super.populateParams();

		OperationColumn col = (OperationColumn) component;
        col.setField(field);
        col.setHeader(header);
        col.setParam(param);
        

	}		
		
}

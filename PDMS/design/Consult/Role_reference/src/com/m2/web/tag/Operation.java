package com.m2.web.tag;

import java.io.Writer;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;

public class Operation extends Component{
	
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
	
	public Operation(ValueStack arg0) {
		super(arg0);
	}		
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean start(Writer writer) {
		
		boolean result = super.start(writer);
		Component com= this.findAncestor(OperationColumn.class);  //寻找最近的父组件
		OperationAppender appender=(OperationAppender)com;        
		appender.addOperation(this);                              //把当前对象注入到父组件
		
		return result;
		
		
	}	

}

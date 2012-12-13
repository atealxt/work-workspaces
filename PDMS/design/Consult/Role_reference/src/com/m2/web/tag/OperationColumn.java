package com.m2.web.tag;


import java.util.*;
import com.opensymphony.xwork2.util.ValueStack;

public class OperationColumn extends Column implements OperationAppender{
	
	private List<Operation> operations=new ArrayList<Operation>();
	
	private String param;
	
	public List<Operation> getOperations(){
		return this.operations;
	}
	
	public OperationColumn(ValueStack arg0) {
		super(arg0);
	}		
	
	
	public void addOperation(Operation op){
		this.operations.add(op);
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	


}

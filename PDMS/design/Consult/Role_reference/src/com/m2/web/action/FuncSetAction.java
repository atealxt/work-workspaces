package com.m2.web.action;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.m2.entity.*;
import com.m2.service.*;
import com.m2.exception.*;
public class FuncSetAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3487861260311636618L;



	private static final Log logger = LogFactory.getLog(FuncSetAction.class);
	
	
	
	private String  name;
	
	private String resource ; 
	
	private String params;
	
	private String description;
	
	private  FuncTree treeNode;
	
	private Integer treeNodeId;
	
	private Function function;
	
	private List  functions ;
	
	private SysFunctionService sysFunctionService;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List getFunctions() {
		return functions;
	}

	public void setFunctions(List functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}


	
	public FuncTree getTreeNode(){
		return this.treeNode;  
	}
	
	public void setTreeNode(int treeNodeId){
		FuncTree treeNode= new FuncTree();
		treeNode.setId(treeNodeId);
		this.treeNode=treeNode;
	}
	
	
	
	
	
	public Integer getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(Integer treeNodeId) {
		this.treeNodeId = treeNodeId;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(int functionId) {
		Function func = new Function();
		func.setId(functionId);
		this.function=func;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public String execute() {
		
		if ("index".equalsIgnoreCase(this.getAction())){
			return this.index();
		}else if ("add".equalsIgnoreCase(this.getAction())){
			return this.add();
		}else if ("del".equalsIgnoreCase(this.getAction())){
			return this.delete();
		}
		
		return ERROR;

	}
	
	private String  index(){
		List functions=null;
		this.setAction("add");
		FuncTree node = new FuncTree();
		node.setId(this.getTreeNodeId());
		try{
			functions = this.getSysFunctionService()
			                .findFunctionsByTreeNode(node);
		}catch(M2Exception e){
			return sysError(e);
		}
		this.setFunctions(functions);
		return INDEX;
		
	}
	
	private String add(){
		Function function=new Function();
		setProperties(function);
		FuncTree node = new FuncTree();
		node.setId(this.getTreeNodeId());
		function.setFuncTree(node);
		try{
			this.getSysFunctionService().addFunction(function);
			List functions = this.getSysFunctionService().findFunctionsByTreeNode(node);
			this.setFunctions(functions);
		}catch(M2Exception e){
			return sysError(e);
		}
		return SUCCESS;
	}
	
	
	private String delete(){
		Function function = this.getFunction();
		
		try{
			this.getSysFunctionService().removeFunction(function);
			List functions = this.getSysFunctionService().findFunctionsByTreeNode(this.getTreeNode());
			this.setFunctions(functions);
		}catch(M2Exception e){
			return sysError(e);
		}
		return SUCCESS;
	}
	
	
	
	private void setProperties(Function function){
		function.setName(this.getName());
		function.setParams(this.getParams());
		function.setResource(this.getResource());
		function.setDescription(this.getDescription());
	}
	
	

}

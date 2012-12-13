package com.m2.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.m2.entity.FuncTree;
import com.m2.exception.M2Exception;
import com.m2.service.SysFunctionService;

public class FuncTreeAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5230821265907600169L;


	private static final Log logger = LogFactory.getLog(FuncTreeAction.class);

	
	private String name;
	
	private String description;
		
	private int id;
	
	private int parentId=0;
	
	private SysFunctionService sysFunctionService;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}
	
	
	public String execute(){
		//Ìí¼Ó¡¢ÐÞ¸ÄÓëÉ¾³ý
		if ("add".equalsIgnoreCase(this.getAction())){
			FuncTree treeNode = new FuncTree();
			treeNode.setParentId(this.getParentId());
			treeNode.setName(this.getName());
			treeNode.setDescription(this.getDescription());
			treeNode.setSort(0);
			try{
				if (!this.getSysFunctionService().hasFuncTreeNode()){
				    treeNode.setParentId(-1);
				    treeNode.setLayer(0);
				}    
				if (treeNode.getParentId()!=-1){
				    FuncTree parentNode = this.getSysFunctionService().findByFuncTreeId(this.getParentId());
				    treeNode.setLayer(parentNode.getLayer()+1);
				}else
					treeNode.setLayer(0);
				this.getSysFunctionService().addFuncTreeNode(treeNode);
			}catch(M2Exception e){
				e.printStackTrace();
				logger.error("add failed",e);
			}
			return SUCCESS;
		}else if ("update".equalsIgnoreCase(this.getAction())){
			try{
				FuncTree treeNode = this.getSysFunctionService().findByFuncTreeId(this.getId()); 
				treeNode.setDescription(this.getDescription());
				treeNode.setName(this.getName());
				this.getSysFunctionService().updateFuncTreeNode(treeNode);
				
			}catch(M2Exception e){
				logger.error(e);
			}
			return SUCCESS;
		}else if ("del".equalsIgnoreCase(this.getAction())){
			try{
				FuncTree treeNode = this.getSysFunctionService().findByFuncTreeId(this.getId()); 
				if (this.getSysFunctionService().hasSubFunctions(treeNode)){
					this.addActionError("Can not del this node: some sub functions exsit.");
					return INPUT;
				}
				if (!this.getSysFunctionService().isLeaf(treeNode)){
					this.addActionError("Can not del this node: it is not a leaf");
					return INPUT;
				}
				this.getSysFunctionService().removeFuncTreeNode(treeNode);
				return SUCCESS;
			}catch(M2Exception e){
				logger.error(e);
			}
		}
		return INPUT;
		
	}
	
	
	
	
	
	

}

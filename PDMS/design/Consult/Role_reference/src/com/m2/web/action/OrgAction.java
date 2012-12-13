package com.m2.web.action;

import com.m2.service.OrgService;
import com.m2.entity.Org;
import com.m2.exception.M2Exception;


/***
 * 
 * @author Augustan  http://yuetong.javaeye.com
 * 
 */


public class OrgAction extends BaseAction{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3892500770901819621L;

	public static final String VIEW = "view";
	
	private String name;
	
	private String description;
	
	private String action;
	
	private int id;
	
	private int parentId;
	
	private OrgService orgService;
	
		
	
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


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



	public OrgService getOrgService() {
		return orgService;
	}


	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}


	public String index(){
		return INPUT;
	}
	
	public String view(){
		return VIEW;
	}	
	
	
	public String execute(){
		
		String action = this.getAction();
		if ("add".equalsIgnoreCase(action))
			return addChildOrg();
		if ("update".equalsIgnoreCase(action))
		    return updateOrg();
		if ("del".equalsIgnoreCase(action))
			return removeOrg();
		if ("view".equalsIgnoreCase(action))
			return view();
		
        return index();
	}
	
	
	
	
	public String addChildOrg(){
		Org child = new Org();
		child.setDescription(getDescription());
		child.setName(getName());
		int parentId = this.getParentId();
		child.setParentId(parentId);
		try{
			this.orgService.addOrg(child);
		}catch(M2Exception e){
			return sysError(e);
		}
		this.setAction("index");
		return SUCCESS;
	}
	
	

	public String updateOrg(){
		try{
			Org org = this.orgService.findOrgById(this.getId());
			if (org==null)
				return INPUT;
			org.setDescription(this.getDescription());
			org.setName(this.getName());
			this.orgService.updateOrg(org);
			return SUCCESS;
		}catch(M2Exception e){
			return sysError(e);		
		}
		
	}
	
	
	public String removeOrg(){
		try{
			Org org = this.orgService.findOrgById(this.getId());
			if (org==null) 
				return INPUT;
			boolean isRemoved=this.orgService.removeOrg(org);
			if (!isRemoved){
				this.addActionError("该机构删除失败,含有下属机构或者被其他对象引用。");
				return INPUT;
			}
			return SUCCESS;
		}catch(M2Exception e){
			return sysError(e);
	    }
		
	}
	
	
	
	
	
	
	

}

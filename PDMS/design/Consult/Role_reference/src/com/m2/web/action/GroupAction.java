package com.m2.web.action;

import com.m2.service.RoleService;
import com.m2.service.GroupService;
import com.m2.entity.M2Group;
import com.m2.entity.M2Role;
import com.m2.exception.M2Exception;
import java.util.*;

public class GroupAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4751799024604346150L;
	
	private int groupid=-1;
	
	private String name;
	
	private String description;
	
	private RoleService roleService;
	
	private GroupService groupService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	private int id=-1;
	
	
	
	//////////////////////////////////////////////////////////
	private List<Integer> roles=new ArrayList<Integer>();
	
	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	
	
	private List<M2Role> rolesAll=new ArrayList<M2Role>();
	
	public List<M2Role> getRolesAll() {
		return rolesAll;
	}

	public void setRolesAll(List<M2Role> rolesAll) {
		this.rolesAll = rolesAll;
	}

	
	////////////////////////////////////////////////////////////////


	@SuppressWarnings("unchecked")
	public String execute(){
		List list=null;
    	try{
    		list = this.getRoleService().findAllRoles();
    		
     	}catch(M2Exception e){
    		e.printStackTrace();
    		this.addActionError("");
    		return INPUT;
    	}	
		for (int i=0;i<list.size();i++){
			M2Role role = (M2Role)list.get(i);
			this.getRolesAll().add(role);
		}
     	
     	
		if ("add".equalsIgnoreCase(this.getAction())){
			return this.addGroup();
			
		}
		if ("update".equalsIgnoreCase(this.getAction())){
			return this.updateGroup();
			
		}		
		
		if ("del".equalsIgnoreCase(this.getAction())){
			return this.delGroup();
			
		}	
		
		
		
		
	
        if (this.getGroupid()==-1){
        	this.setPageTitle(this.getText("group.add.title"));
        	this.setAction("add");
        	return INPUT;
        }
		
        try{
        	
        	M2Group group = this.getGroupService().findGroupById(this.getGroupid());
        	this.setName(group.getName());
        	this.setDescription(group.getDescription());
        	this.setAction("update");
        	Set set= group.getRoles();
        	for(Iterator i = set.iterator();i.hasNext();){
        		M2Role g = (M2Role)i.next();
        		this.getRoles().add(g.getId());
        		
        	}

        	
        }catch(M2Exception e){
        	e.printStackTrace();
        }
        
        
        
		return INPUT;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String addGroup(){
		
		List list = this.getRoles();
		Set roles = new HashSet();
		try{
		    for (int i=0;i<list.size();i++){
			    Integer roleId = (Integer)list.get(i);
			    M2Role role = this.getRoleService().findRoleById(roleId);
			    roles.add(role);
		    }
		    M2Group group = new M2Group();
		    group.setName(this.getName());
		    group.setDescription(this.getDescription());
		    group.setRoles(roles);
			this.getGroupService().addGroup(group);
			
		}catch(M2Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String updateGroup(){
		
		M2Group gp =null;
		try{
			gp= this.getGroupService().findGroupById(this.getGroupid());
			gp.setName(this.getName());
			gp.setDescription(this.getDescription());
			Set roles = new HashSet();
		    for (int i=0;i<this.getRoles().size();i++){
			    Integer roleId = (Integer)getRoles().get(i);
			    M2Role role = this.getRoleService().findRoleById(roleId);
			    roles.add(role);
		    }			
			gp.setRoles(roles);
			this.getGroupService().updateGroup(gp);
		}catch(M2Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
		
		
	}
	
	
	public String delGroup(){
		try{
			M2Group gp =  this.getGroupService().findGroupById(this.getGroupid());
			this.getGroupService().deleteGroup(gp);
		}catch(M2Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
		
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}

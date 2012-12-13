package com.m2.web.action;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.m2.entity.*;
import com.m2.exception.M2Exception;
import com.m2.service.OrgService;
import com.m2.service.UserService;
import com.m2.service.RoleService;
import com.m2.web.ui.LoginName;

/***
 * 
 * @author Augustan  http://www.m2-soft.com
 * 
 */

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8958060865136142220L;
	
	public static final String ROLEINDEX  = "roleIndex";
	
	public static final String ROLEINPUT = "roleInput";
	
	
	private int userid = -1;
	
	private LoginName loginName=new LoginName();
	
	private String realName;
	
	private String email;
	
	private int orgId = -1;
	
	private String orgName;
	
	private int groupId = -1;
	
	private Date birthday;
	
	private String address;
	
	private String description;
	
	private String tel;
	
	private int isEnabled =0 ;
	
	private OrgService  orgService;
	
	private UserService userService;
	
	private RoleService roleService;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	public LoginName getLoginName() {
		return loginName;
	}

	public void setLoginName(LoginName loginName) {
		this.loginName=loginName;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
			
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public OrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	
	
	private List<Integer> roles = new ArrayList<Integer>();
	
	private List<Role> rolesAll = new ArrayList<Role>();
		
	
	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public List<Role> getRolesAll() {
		return rolesAll;
	}

	public void setRolesAll(List<Role> rolesAll) {
		this.rolesAll = rolesAll;
	}

	public String execute(){
		
		if ("add".equalsIgnoreCase(this.getAction()))
			return addUser();
		
		if ("update".equalsIgnoreCase(this.getAction()))
		    return updateUser();
		
		if ("del".equalsIgnoreCase(this.getAction()))
		    return delUser();		
		
		if (("forRoleSet").equalsIgnoreCase(this.getAction())){
			return roleIndex();
		}
		
		if (("roleSet").equalsIgnoreCase(this.getAction())){  //这里为用户分配相关的角色
			return roleSet();
		}
		
		if (("forAdd").equalsIgnoreCase(this.getAction())){       // 转到新建页面。
			this.setPageTitle(this.getText("user.add.pageTitle"));  //添加和修改共用同一页面，需要设置特定的标题。
			this.setSubmit(this.getText("user.add.submit"));    
			this.setAction("add");
			return FORADD;
		}
		
		if (!("forUpdate").equalsIgnoreCase(this.getAction())&&(!("detail").equalsIgnoreCase(this.getAction()))){
			this.addActionError(this.getText("user.error.invalidAction"));
			return ERROR;
		}
		
		
		User user=null;
		try{
			user = this.getUserService().findUserById(this.getUserid());
		    if (user==null){
			    this.addActionError(this.getText("user.update.error.noSuchUser"));
		        return ERROR;
		    }

		    this.setOrgId(user.getOrg().getId());
		    this.setOrgName(user.getOrg().getName());
		
		}catch(M2Exception e){
			e.printStackTrace();
		}	

		
		this.getLoginName().setDisabled(true);
		this.getLoginName().setLoginName(user.getLoginName());
		

		this.setIsEnabled(user.getIsEnabled());
		this.setRealName(user.getRealName());
		this.setDescription(user.getDescription());
		
		if ("detail".equalsIgnoreCase(this.getAction()))     //转向查看页面
		    return DETAIL;
		
		this.setAction("update");
		this.setPageTitle(this.getText("user.update.pageTitle"));   
		this.setSubmit(this.getText("user.update.submit"));	
		
		return FORUPDATE;                                          //转向修改页面
	}
	
	/****
	 * 
	 * 新增用户
	 * @return
	 */

	public String addUser(){

        User user= new User();
        user.setRealName(this.getRealName());
        user.setLoginName(this.getLoginName().getLoginName());
        user.setDescription(this.getDescription());  
        
       
        try{
            Org org = this.getOrgService().findOrgById(this.getOrgId());
        	user.setOrg(org);
        	if (org==null){
        		this.addActionError(this.getText("user.add.error.noSuchOrg"));
        		return ERROR;
        	}
        	this.getUserService().addUser(user);

        }catch(M2Exception e){
        	e.printStackTrace();
        	this.addActionError(e.toString());
        	return sysError(e);
        }
    	return SUCCESS;        
	}
	
	/****
	 * 
	 * 修改用户信息
	 * @return
	 */	
	public String updateUser(){
        try{
        	User user= this.getUserService().findUserById(this.getUserid());
        	if (user==null){
        		this.addActionError(this.getText("user.update.error.noSuchUser"));
        		return ERROR;
        	}
        	user.setRealName(this.getRealName());
    
        	user.setDescription(this.getDescription());
        	Org org = this.getOrgService().findOrgById(this.getOrgId());
        	user.setOrg(org);

        	
        	if (org==null){
        		this.addActionError(this.getText("user.update.error.noSuchOrg"));
        		return ERROR;
        	}
        	this.getUserService().updateUser(user);
        	return SUCCESS;
        }catch(M2Exception e){
        	this.addActionError(e.toString());
        	return ERROR;
        }
	}
	
	/****
	 * 
	 * 删除用户，需要检查约束性
	 * @return
	 */	
	
	public String delUser(){
		try{
			this.getUserService().delUserById(this.getUserid());
			return SUCCESS;
		}catch(M2Exception e){
			this.addActionError(e.toString());
			return ERROR;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public String   roleIndex(){

		try{		
			this.setRolesAll( this.getRoleService().findAllRoles());
			User u = this.getUserService().findUserById(this.getUserid());
			this.setRealName(u.getRealName());
			LoginName n = new LoginName();
			n.setLoginName(u.getLoginName());
			this.setLoginName(n);
			Set roles = u.getRoles();
			this.setAction("roleSet");
			for(Iterator i=roles.iterator();i.hasNext();){
				Role role = (Role)i.next();
				this.getRoles().add(role.getId());
			}
		}catch(M2Exception e){
			e.printStackTrace();
			return INPUT;
		}
		return ROLEINDEX;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public String roleSet(){
		
		try{
			User user = this.getUserService().findUserById(this.getUserid());
			Set roles = new HashSet();
			for (int i=0;i<this.getRoles().size();i++){
				int id = this.getRoles().get(i);
				Role role = this.getRoleService().findRoleById(id);
				roles.add(role);
			}
			user.setRoles(roles);
			this.getUserService().updateUser(user);
		}catch(M2Exception e){
			
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}
	
	

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
	

	

}
package com.m2.web.action;

import com.m2.entity.*;
import com.m2.service.*;
import com.m2.exception.M2Exception;
import com.m2.common.Util;
import com.m2.web.UserSession;
import com.m2.common.Constant;
import java.util.*;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction  extends BaseAction implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6036924541466141996L;

	private String loginName;
	
	private String password;
	
	private String validateCode;
	
	private UserService userService;
	
	private SysFunctionService sysFunctionService;
	
	private Map  session;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	public String execute(){
		String action = this.getAction();
		if ("login".equalsIgnoreCase(action))
		    return login();
		
		return index();
	}
	
	
	
	public String index(){
		this.setAction("login");
		return INPUT;
		
	}
	

	@SuppressWarnings("unchecked")
	public String login(){
		
		if (!Util.isNotBlank(this.getLoginName())){
			this.addActionError("e.null.name");
			return INPUT;
		}
		if (!Util.isNotBlank(this.getPassword())){
			this.addActionError("e.null.pwd");
			return INPUT;
		}
		User user = null;
		
		try{
		    user =  this.getUserService().findUserByLoginName(this.getLoginName());
		}catch(M2Exception e){
			e.printStackTrace();//////////////
		}
		Group  group = user.getGroup();
		
		UserSession userSession =  new UserSession();
		userSession.setGroup(group);
		userSession.setOrg(user.getOrg());
		userSession.setLastActiveTime(0);
		userSession.setRealName(user.getRealName());
		userSession.setLoginName(user.getLoginName());
		Set roles =user.getRoles();
		
		if (group!=null){
		    Set groupRoles = group.getRoles();
		    roles.addAll(groupRoles);
		}
		Set resources = mergeResources(roles);
		userSession.setResources(resources);
		this.getSession().put(Constant.USER_SESSION, userSession);
		return SUCCESS;
	}
	
	
	@SuppressWarnings("unchecked")
	public Set mergeResources(Set roles){
		Set resources = new HashSet();
		for (Iterator it=roles.iterator();it.hasNext();){
			Role role = (Role)it.next();
			List functions=role.getFunctions();
			for (int i=0;i<functions.size();i++){
				Integer pm = (Integer)functions.get(i);
				try{
				    Function function =this.getSysFunctionService().findByFunctionId(pm);
				   // if (permission==null) {System.out.println(pm);break;}
				    if (function==null) {System.out.println(pm);break;}
				    String [] actions = function.getParams().split(",");
				    for(int j=0;j<actions.length;j++){
					    resources.add(function.getResource()+"?action="+actions[j]);
				    }
				}catch(M2Exception e){
					e.printStackTrace();////////////////////////
				}
			}
		}
		return resources;
	}

}

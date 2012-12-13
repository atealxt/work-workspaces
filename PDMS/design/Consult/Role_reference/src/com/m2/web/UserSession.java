package com.m2.web;

import java.util.*;
import com.m2.entity.*;

public class UserSession {
	
	private String  loginName;
	
	private String  realName;
	
	private String  email;
	
	private String  indentityCode;
	
	private long lastActiveTime;
			
	private Org   org;
	
	private Group   group;
	
	private Set  roels = new HashSet();
	
	private Set resources = new HashSet();   

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public long getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(long lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getIndentityCode() {
		return indentityCode;
	}

	public void setIndentityCode(String indentityCode) {
		this.indentityCode = indentityCode;
	}

	public Set getRoels() {
		return roels;
	}

	public void setRoels(Set roels) {
		this.roels = roels;
	}

	public Set getResources() {
		return resources;
	}

	public void setResources(Set resources) {
		this.resources = resources;
	}
	
	

	
	


	
	
	
	

	
	
	
	
	

}

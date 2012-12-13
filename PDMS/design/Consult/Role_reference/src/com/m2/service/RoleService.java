package com.m2.service;

import java.util.*;
import com.m2.entity.*;
import com.m2.exception.M2Exception;

public interface RoleService {
	
	public List findAllRoles()throws M2Exception;
	
	public List findRoleIdsByGroupId(int groupId)throws M2Exception;
	
	public Role findRoleById(int id) throws M2Exception;
	
	public void addRole(Role role) throws M2Exception;
	
	public void updateRole(Role role) throws M2Exception;
	
	public void delRole(Role role) throws M2Exception;
	
	//public String delRole(Role role) throws M2Exception;
	

}

package com.m2.service.impl;

import java.util.List;
import com.m2.dao.RoleDAO;
import com.m2.entity.Role;
import com.m2.exception.M2Exception;
import com.m2.service.RoleService;

public class RoleServiceImpl implements RoleService{
	
	private  RoleDAO roleDAO;
	

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List findAllRoles() throws M2Exception{
		
		try{
			return this.getRoleDAO().findAllRoles();
		}catch(Exception e){
			throw new M2Exception(e);
		}
		
	}
	
	public List findRoleIdsByGroupId(int groupId)throws M2Exception{
		
		try{
			return this.getRoleDAO().findRoleIdsByGroupId(groupId);
		}catch(Exception e){
			throw new M2Exception(e);
		}
		
		
	}
	
	public Role findRoleById(int id)throws M2Exception{
		
		try{
			return this.getRoleDAO().findRoleById(id);
		}catch(Exception e){
			throw new M2Exception(e);
		}
		
	}
	
	public void addRole(Role role) throws M2Exception{
		try{
			this.getRoleDAO().addRole(role);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	public void updateRole(Role role) throws M2Exception{
		try{
			this.getRoleDAO().updateRole(role);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	public void delRole(Role role) throws M2Exception{
		try{
			this.getRoleDAO().delRole(role);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	
	
	
}

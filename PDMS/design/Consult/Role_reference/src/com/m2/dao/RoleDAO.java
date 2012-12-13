package com.m2.dao;

import java.util.List;

import com.m2.entity.Role;

public interface RoleDAO extends BaseDAO{
	
	public List findAllRoles();
	
	public List findRoleIdsByGroupId(int groupId);
	
	public Role findRoleById(int id);
	
	public void addRole(Role role);
	
	public void updateRole(Role role);
	
	public void delRole(Role role);

	
	
}

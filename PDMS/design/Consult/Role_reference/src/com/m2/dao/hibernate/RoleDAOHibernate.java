package com.m2.dao.hibernate;

import java.util.List;
import com.m2.dao.RoleDAO;
import com.m2.entity.Role;


public class RoleDAOHibernate extends BaseDAOHibernate implements RoleDAO{
	
	public static final String HQL_SELECT_ALL_ROLES= " from Role  ";
	
	public static final String HQL_SELECT_ROLEIDS= "select roleId  from GroupRole m where m.groupId=?";

	public List findAllRoles(){
		List list = getHibernateTemplate().find(HQL_SELECT_ALL_ROLES);
		return list;
	}
	
	public List findRoleIdsByGroupId(int groupId){
		List list = findByParam(HQL_SELECT_ROLEIDS,groupId);
		return list;
	}
	
	public Role findRoleById(int id){
		return (Role)findById("Role",id);
	}
	
	
	public void addRole(Role role){
		save(role);
	}
	
	public void updateRole(Role role){
		update(role);
	}
	
	public void delRole(Role role){
		delete(role);
	}

	
	
	
	
}

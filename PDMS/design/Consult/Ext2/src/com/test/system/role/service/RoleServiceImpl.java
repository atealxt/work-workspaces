package com.test.system.role.service;

import java.util.List;

import com.test.system.role.ibatis.RoleDAOImpl;
import com.test.system.role.model.RoleModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class RoleServiceImpl implements IRoleService{
	
	private RoleDAOImpl roleDAO;
	
	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}

	public int createRole(RoleModel model) {
		return roleDAO.createRole(model);
	}

	public int deleteRole(RoleModel model) {
		return roleDAO.deleteRole(model);
	}

	public RoleModel getRole(RoleModel model) {
		return roleDAO.getRole(model);
	}

	public int getRoleCount(RoleModel model) {
		return roleDAO.getRoleCount(model);
	}

	public List<RoleModel> getRoleList(RoleModel model) {
		return roleDAO.getRoleList(model);
	}

	public List<RoleModel> getRoleList(int start, int count, RoleModel model) {
		return roleDAO.getRoleList(start, count, model);
	}

	public int updateRole(RoleModel model) {
		return roleDAO.updateRole(model);
	}


}



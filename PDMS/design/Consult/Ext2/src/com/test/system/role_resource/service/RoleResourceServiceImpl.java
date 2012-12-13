package com.test.system.role_resource.service;

import java.util.List;

import com.test.system.role_resource.ibatis.RoleResourceDAOImpl;
import com.test.system.role_resource.model.RoleResourceModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class RoleResourceServiceImpl implements IRoleResourceService{
	
	private RoleResourceDAOImpl roleresourceDAO;
	
	public void setRoleresourceDAO(RoleResourceDAOImpl roleresourceDAO) {
		this.roleresourceDAO = roleresourceDAO;
	}

	public int createRoleResource(RoleResourceModel model) {
		return roleresourceDAO.createRoleResource(model);
	}

	public int deleteRoleResource(RoleResourceModel model) {
		return roleresourceDAO.deleteRoleResource(model);
	}

	public RoleResourceModel getRoleResource(RoleResourceModel model) {
		return roleresourceDAO.getRoleResource(model);
	}

	public int getRoleResourceCount(RoleResourceModel model) {
		return roleresourceDAO.getRoleResourceCount(model);
	}

	public List<RoleResourceModel> getRoleResourceList(RoleResourceModel model) {
		return roleresourceDAO.getRoleResourceList(model);
	}

	public List<RoleResourceModel> getRoleResourceList(int start, int count, RoleResourceModel model) {
		return roleresourceDAO.getRoleResourceList(start, count, model);
	}

	public int updateRoleResource(RoleResourceModel model) {
		return roleresourceDAO.updateRoleResource(model);
	}


}



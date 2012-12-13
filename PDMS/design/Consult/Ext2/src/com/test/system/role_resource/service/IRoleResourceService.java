package com.test.system.role_resource.service;

import java.util.List;

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
public interface IRoleResourceService {
	
	//
	public int createRoleResource(RoleResourceModel model);
	//
    public int updateRoleResource(RoleResourceModel model);
    //
    public int deleteRoleResource(RoleResourceModel model);
    //
    public List<RoleResourceModel> getRoleResourceList(RoleResourceModel model);
    //
    public int getRoleResourceCount(RoleResourceModel model);
    //
    public List<RoleResourceModel> getRoleResourceList(int start,int count,RoleResourceModel model);
    //
    public RoleResourceModel getRoleResource(RoleResourceModel model);
    
}



package com.test.system.role.service;

import java.util.List;

import com.test.system.role.model.RoleModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public interface IRoleService {
	
	//
	public int createRole(RoleModel model);
	//
    public int updateRole(RoleModel model);
    //
    public int deleteRole(RoleModel model);
    //
    public List<RoleModel> getRoleList(RoleModel model);
    //
    public int getRoleCount(RoleModel model);
    //
    public List<RoleModel> getRoleList(int start,int count,RoleModel model);
    //
    public RoleModel getRole(RoleModel model);
    
}



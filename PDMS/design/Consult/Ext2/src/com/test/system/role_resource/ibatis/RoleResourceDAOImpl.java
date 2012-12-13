package com.test.system.role_resource.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

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
public class RoleResourceDAOImpl  extends SqlMapClientDaoSupport implements IRoleResourceDAO{

	//
	public int createRoleResource(RoleResourceModel model){
		return getSqlMapClientTemplate().update("createRoleResource", model);
	}
	//
    public int updateRoleResource(RoleResourceModel model){
    	return getSqlMapClientTemplate().update("updateRoleResource", model);
    }
    //
    public int deleteRoleResource(RoleResourceModel model){
    	return getSqlMapClientTemplate().delete("deleteRoleResource", model);
    }
    //
    public List<RoleResourceModel> getRoleResourceList(RoleResourceModel model){
		List<RoleResourceModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getRoleResourceList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<RoleResourceModel>();
		}
		return list;
    }
    //
    public int getRoleResourceCount(RoleResourceModel model){
    	return (Integer)getSqlMapClientTemplate().queryForObject("getRoleResourceCount",model);
    }
    //
    public List<RoleResourceModel> getRoleResourceList(int start,int count,RoleResourceModel model){
    	List<RoleResourceModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getRoleResourceList", model,start,count);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<RoleResourceModel>();
		}
		return list;
    }
    //
    public RoleResourceModel getRoleResource(RoleResourceModel model){
    	return (RoleResourceModel)getSqlMapClientTemplate().queryForObject("getRoleResource",model);
    }

}


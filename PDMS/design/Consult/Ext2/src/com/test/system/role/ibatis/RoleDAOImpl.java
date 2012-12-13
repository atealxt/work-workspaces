package com.test.system.role.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

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
public class RoleDAOImpl  extends SqlMapClientDaoSupport implements IRoleDAO{

	//
	public int createRole(RoleModel model){
		return getSqlMapClientTemplate().update("createRole", model);
	}
	//
    public int updateRole(RoleModel model){
    	return getSqlMapClientTemplate().update("updateRole", model);
    }
    //
    public int deleteRole(RoleModel model){
    	return getSqlMapClientTemplate().delete("deleteRole", model);
    }
    //
    public List<RoleModel> getRoleList(RoleModel model){
		List<RoleModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getRoleList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<RoleModel>();
		}
		return list;
    }
    //
    public int getRoleCount(RoleModel model){
    	return (Integer)getSqlMapClientTemplate().queryForObject("getRoleCount",model);
    }
    //
    public List<RoleModel> getRoleList(int start,int count,RoleModel model){
    	List<RoleModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getRoleList", model,start,count);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<RoleModel>();
		}
		return list;
    }
    //
    public RoleModel getRole(RoleModel model){
    	return (RoleModel)getSqlMapClientTemplate().queryForObject("getRole",model);
    }

}


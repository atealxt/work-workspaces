package com.test.system.resource.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.test.system.resource.model.ResourceModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class ResourceDAOImpl  extends SqlMapClientDaoSupport implements IResourceDAO{

	//
	public int createResource(ResourceModel model){
		return getSqlMapClientTemplate().update("createResource", model);
	}
	//
    public int updateResource(ResourceModel model){
    	return getSqlMapClientTemplate().update("updateResource", model);
    }
    //
    public int deleteResource(ResourceModel model){
    	return getSqlMapClientTemplate().delete("deleteResource", model);
    }
    //
    public List<ResourceModel> getResourceList(ResourceModel model){
		List<ResourceModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getResourceList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<ResourceModel>();
		}
		return list;
    }
    //
    public int getResourceCount(ResourceModel model){
    	return (Integer)getSqlMapClientTemplate().queryForObject("getResourceCount",model);
    }
    //
    public List<ResourceModel> getResourceList(int start,int count,ResourceModel model){
    	List<ResourceModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getResourceList", model,start,count);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<ResourceModel>();
		}
		return list;
    }
    //
    public ResourceModel getResource(ResourceModel model){
    	return (ResourceModel)getSqlMapClientTemplate().queryForObject("getResource",model);
    }
    
    //获取用户资源
    public List<ResourceModel> getUserResourceList(ResourceModel model){
    	List<ResourceModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getUserResourceList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<ResourceModel>();
		}
		return list;
    }

}


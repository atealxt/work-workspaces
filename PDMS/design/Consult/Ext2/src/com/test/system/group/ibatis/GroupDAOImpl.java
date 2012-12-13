package com.test.system.group.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.test.system.group.model.GroupModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class GroupDAOImpl  extends SqlMapClientDaoSupport implements IGroupDAO{

	//
	public int createGroup(GroupModel model){
		return getSqlMapClientTemplate().update("createGroup", model);
	}
	//
    public int updateGroup(GroupModel model){
    	return getSqlMapClientTemplate().update("updateGroup", model);
    }
    //
    public int deleteGroup(GroupModel model){
    	return getSqlMapClientTemplate().delete("deleteGroup", model);
    }
    //
    public List<GroupModel> getGroupList(GroupModel model){
		List<GroupModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getGroupList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<GroupModel>();
		}
		return list;
    }
    //
    public int getGroupCount(GroupModel model){
    	return (Integer)getSqlMapClientTemplate().queryForObject("getGroupCount",model);
    }
    //
    public List<GroupModel> getGroupList(int start,int count,GroupModel model){
    	List<GroupModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getGroupList", model,start,count);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<GroupModel>();
		}
		return list;
    }
    //
    public GroupModel getGroup(GroupModel model){
    	return (GroupModel)getSqlMapClientTemplate().queryForObject("getGroup",model);
    }

}


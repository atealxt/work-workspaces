package com.test.system.user.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.test.system.user.model.UserModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class UserDAOImpl  extends SqlMapClientDaoSupport implements IUserDAO{

	//
	public int createUser(UserModel model){
		return getSqlMapClientTemplate().update("createUser", model);
	}
	//
    public int updateUser(UserModel model){
    	return getSqlMapClientTemplate().update("updateUser", model);
    }
    //
    public int deleteUser(UserModel model){
    	return getSqlMapClientTemplate().delete("deleteUser", model);
    }
    //
    public List<UserModel> getUserList(UserModel model){
		List<UserModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getUserList", model);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<UserModel>();
		}
		return list;
    }
    //
    public int getUserCount(UserModel model){
    	return (Integer)getSqlMapClientTemplate().queryForObject("getUserCount",model);
    }
    //
    public List<UserModel> getUserList(int start,int count,UserModel model){
    	List<UserModel> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("getUserList", model,start,count);
		} catch (Exception e) {
			  
		}
		if(list == null){
			list = new ArrayList<UserModel>();
		}
		return list;
    }
    //
    public UserModel getUser(UserModel model){
    	return (UserModel)getSqlMapClientTemplate().queryForObject("getUser",model);
    }

}


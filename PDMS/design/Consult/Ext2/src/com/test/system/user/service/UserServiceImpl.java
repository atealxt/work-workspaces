package com.test.system.user.service;

import java.text.SimpleDateFormat;
import java.util.List;

import com.test.system.user.ibatis.UserDAOImpl;
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
public class UserServiceImpl implements IUserService{
	
	private UserDAOImpl userDAO;
	
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public int createUser(UserModel model) {
		return userDAO.createUser(model);
	}

	public int deleteUser(UserModel model) {
		return userDAO.deleteUser(model);
	}

	public UserModel getUser(UserModel model) {
		return userDAO.getUser(model);
	}

	public int getUserCount(UserModel model) {
		return userDAO.getUserCount(model);
	}

	public List<UserModel> getUserList(UserModel model) {
		List<UserModel> list = userDAO.getUserList(model);
		for(UserModel user : list){
			user.setCreatetime(user.getCreatetime().substring(0,19));
		}
		return list;
	}

	public List<UserModel> getUserList(int start, int count, UserModel model) {
		List<UserModel> list = userDAO.getUserList(start, count, model);
		for(UserModel user : list){
			user.setCreatetime(user.getCreatetime().substring(0,19));
		}
		return list; 
	}

	public int updateUser(UserModel model) {
		return userDAO.updateUser(model);
	}


}



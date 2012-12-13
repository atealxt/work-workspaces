package com.m2.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.m2.entity.*;
import com.m2.exception.M2Exception;
import com.m2.service.UserService;
import com.m2.dao.UserDAO;



public class UserServiceImpl implements UserService{
	
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	private UserDAO userDAO;
		
	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public void addUser(User user) throws M2Exception{
		try{
			this.userDAO.addUser(user);
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	
	public void updateUser(User user) throws M2Exception{
		try{
			this.userDAO.updateUser(user);
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}	
	}
	
	
	
	//检查是否符合删除的条件？
	public boolean delUser(User user) throws M2Exception{
		try{
			this.userDAO.delUser(user);
			return true;
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	
	public boolean delUserById(int userid) throws M2Exception{
		try{
			this.userDAO.delUserById(userid);
			return true;
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}	
    }
	
	
	public User findUserById(int id) throws M2Exception{
		try{
			return this.userDAO.findUserById(id);
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	
	public User findUserByLoginName(String loginName) throws M2Exception{
		
		try{
			return this.userDAO.findUserByLoginName(loginName);
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}		
	}

	
	
	

}

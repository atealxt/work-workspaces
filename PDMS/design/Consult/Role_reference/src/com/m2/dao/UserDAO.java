package com.m2.dao;

import com.m2.entity.User;


public interface UserDAO extends BaseDAO{
	
	
	public static final String FIND_USER_BY_LOGIN_NAME = " from User u where u.loginName=?";
	
	public static final String DEL_USER_BY_ID = " delete from User u where u.id=?";
	
	public void addUser(User user) ;
	
	
	public void updateUser(User user) ;
	
	
	public void delUser(User user) ;
	
	
	public void delUserById(final int userid);
	
	
	public User findUserById(int id);
	
	
	public User findUserByLoginName(String loginName);

}

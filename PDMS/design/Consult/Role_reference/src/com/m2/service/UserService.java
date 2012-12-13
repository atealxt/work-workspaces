package com.m2.service;

import com.m2.exception.M2Exception;
import com.m2.entity.User;

public interface UserService {
	
	public void addUser(User user) throws M2Exception;
	
	
	public void updateUser(User user) throws M2Exception;
	
	
	public boolean delUser(User user) throws M2Exception;
	
	
	public boolean delUserById(int userid) throws M2Exception;
	
	
	public User findUserById(int id) throws M2Exception;
	
	
	public User findUserByLoginName(String loginName) throws M2Exception;

	
	

}

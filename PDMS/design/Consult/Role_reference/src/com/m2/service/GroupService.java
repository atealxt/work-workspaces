package com.m2.service;

import com.m2.entity.Group;
import com.m2.exception.*;

public interface GroupService {
	
	public void addGroup(Group gp) throws M2Exception;
	
	public void updateGroup(Group gp) throws M2Exception;
	
	public void deleteGroup(Group gp) throws M2Exception;
	
	public Group findGroupById(int id) throws M2Exception;

}

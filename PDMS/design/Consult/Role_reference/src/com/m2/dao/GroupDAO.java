package com.m2.dao;

import com.m2.entity.Group;;

public interface GroupDAO extends BaseDAO{
	
	public void addGroup(Group gp) ;
	
	public void updateGroup(Group gp);
	
	public void delGroup(Group gp);
	

}

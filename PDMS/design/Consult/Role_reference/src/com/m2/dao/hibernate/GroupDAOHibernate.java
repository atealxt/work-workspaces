package com.m2.dao.hibernate;

import com.m2.dao.GroupDAO;
import com.m2.entity.Group;

public class GroupDAOHibernate extends BaseDAOHibernate implements GroupDAO{
	
	public void addGroup(Group gp){
		save(gp);
	}
	
	
	
	public void updateGroup(Group gp){
		update(gp);
	}
	
	
	public void delGroup(Group gp){
		delete(gp);
	}
	

}

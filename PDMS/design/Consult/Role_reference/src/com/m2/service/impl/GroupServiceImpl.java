package com.m2.service.impl;

import com.m2.service.GroupService;
import com.m2.dao.GroupDAO;
import com.m2.exception.M2Exception;
import com.m2.entity.Group;

public class GroupServiceImpl implements GroupService{

	private GroupDAO groupDAO;

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	
	public Group findGroupById(int id) throws M2Exception {
		try{
			Object o = this.groupDAO.findById("M2Group", id);
			return (Group)o;
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	

	
	public void addGroup(Group gp) throws M2Exception{
		try{
			this.getGroupDAO().addGroup(gp);
			
		}catch(Exception e){
			throw new M2Exception(e);
		}
		
		
	}
	
	public void updateGroup(Group gp) throws M2Exception{
		

		try{
			this.getGroupDAO().updateGroup(gp);
			
		}catch(Exception e){
			throw new M2Exception(e);
		}
		
		
			
	}
	
	public void deleteGroup(Group gp) throws M2Exception{
		
		try{
			this.getGroupDAO().delGroup(gp);
			
		}catch(Exception e){
			throw new M2Exception(e);
		}
				
	}
	
	

	
}

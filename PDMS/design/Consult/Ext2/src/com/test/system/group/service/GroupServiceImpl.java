package com.test.system.group.service;

import java.util.List;

import com.test.system.group.ibatis.GroupDAOImpl;
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
public class GroupServiceImpl implements IGroupService{
	
	private GroupDAOImpl groupDAO;
	
	public void setGroupDAO(GroupDAOImpl groupDAO) {
		this.groupDAO = groupDAO;
	}

	public int createGroup(GroupModel model) {
		return groupDAO.createGroup(model);
	}

	public int deleteGroup(GroupModel model) {
		return groupDAO.deleteGroup(model);
	}

	public GroupModel getGroup(GroupModel model) {
		return groupDAO.getGroup(model);
	}

	public int getGroupCount(GroupModel model) {
		return groupDAO.getGroupCount(model);
	}

	public List<GroupModel> getGroupList(GroupModel model) {
		return groupDAO.getGroupList(model);
	}

	public List<GroupModel> getGroupList(int start, int count, GroupModel model) {
		return groupDAO.getGroupList(start, count, model);
	}

	public int updateGroup(GroupModel model) {
		return groupDAO.updateGroup(model);
	}


}



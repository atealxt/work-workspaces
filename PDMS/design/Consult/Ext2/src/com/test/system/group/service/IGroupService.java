package com.test.system.group.service;

import java.util.List;

import com.test.system.group.model.GroupModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public interface IGroupService {
	
	//
	public int createGroup(GroupModel model);
	//
    public int updateGroup(GroupModel model);
    //
    public int deleteGroup(GroupModel model);
    //
    public List<GroupModel> getGroupList(GroupModel model);
    //
    public int getGroupCount(GroupModel model);
    //
    public List<GroupModel> getGroupList(int start,int count,GroupModel model);
    //
    public GroupModel getGroup(GroupModel model);
    
}



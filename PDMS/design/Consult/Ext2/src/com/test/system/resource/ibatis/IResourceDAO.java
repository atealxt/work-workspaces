package com.test.system.resource.ibatis;

import java.util.List;
import com.test.system.resource.model.ResourceModel;
 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public interface IResourceDAO {
	
	//
	public int createResource(ResourceModel model);
	//
    public int updateResource(ResourceModel model);
    //
    public int deleteResource(ResourceModel model);
    //
    public List<ResourceModel> getResourceList(ResourceModel model);
    //
    public int getResourceCount(ResourceModel model);
    //
    public List<ResourceModel> getResourceList(int start,int count,ResourceModel model);
    //
    public ResourceModel getResource(ResourceModel model);
    //��ȡ�û���Դ
    public List<ResourceModel> getUserResourceList(ResourceModel model);
    
}



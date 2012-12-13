package com.test.system.resource.service;

import java.util.List;

import com.test.system.resource.ibatis.ResourceDAOImpl;
import com.test.system.resource.model.ResourceModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class ResourceServiceImpl implements IResourceService{
	
	private ResourceDAOImpl resourceDAO;
	
	public void setResourceDAO(ResourceDAOImpl resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	public int createResource(ResourceModel model) {
		return resourceDAO.createResource(model);
	}

	public int deleteResource(ResourceModel model) {
		return resourceDAO.deleteResource(model);
	}

	public ResourceModel getResource(ResourceModel model) {
		return resourceDAO.getResource(model);
	}

	public int getResourceCount(ResourceModel model) {
		return resourceDAO.getResourceCount(model);
	}

	public List<ResourceModel> getResourceList(ResourceModel model) {
		return resourceDAO.getResourceList(model);
	}

	public List<ResourceModel> getResourceList(int start, int count, ResourceModel model) {
		return resourceDAO.getResourceList(start, count, model);
	}

	public int updateResource(ResourceModel model) {
		return resourceDAO.updateResource(model);
	}

	//获取用户资源
    public List<ResourceModel> getUserResourceList(ResourceModel model){
    	return resourceDAO.getUserResourceList(model);
    }
}



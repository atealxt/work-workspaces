package com.test.system.group.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import com.test.system.group.model.GroupModel;
import com.test.system.group.service.GroupServiceImpl;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class GroupAction extends ActionSupport implements ModelDriven<GroupModel>,Preparable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private GroupModel model;
	
	private List<GroupModel> modelList; 
	
	private GroupServiceImpl groupService;
	
	public String create(){
		
		return "success";
	}
	
	public String update() {

		return "success";
	}

	public String delete() {
		
		return "success";
	}

	public String view() {
		
		
		return "success";
	}
	
	public String list() {
		
		
		return "success";
	}
	
	//----------------------------------------------------------------------
	
	public void setGroupService(GroupServiceImpl groupService) {
		this.groupService = groupService;
	}
	
	public List<GroupModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<GroupModel> modelList) {
		this.modelList = modelList;
	}
	
	public void setModel(GroupModel model) {
		this.model = model;
	}

	public GroupModel getModel() {

		return model;
	}	
	
	public void prepare() throws Exception {
		model = new GroupModel();
		modelList = new ArrayList<GroupModel>();
	}
	
}


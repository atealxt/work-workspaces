package com.test.system.role.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.test.system.role.model.RoleModel;
import com.test.system.role.service.RoleServiceImpl;
import com.test.system.role_resource.model.RoleResourceModel;
import com.test.system.role_resource.service.RoleResourceServiceImpl;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class RoleAction extends ActionSupport implements ModelDriven<RoleModel>,Preparable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private RoleModel model;
	
	private int start;
	
	private int limit;
	
	private String jsonData;
	
	private List<RoleModel> modelList; 
	
	private RoleServiceImpl roleService;
	
	private RoleResourceServiceImpl roleresourceService;
	
	public String create(){
		
		model.setStatus("1");
		roleService.createRole(model);	 
		return null;
	}
	
	public String update() {

		roleService.updateRole(model);		
		return null;
	}

	public String delete() {
		
		log.debug("删除角色ID=="+jsonData);
		JSONArray json = JSONArray.fromObject(jsonData);
		List<RoleModel> list = (ArrayList<RoleModel>) JSONArray.toCollection(json, RoleModel.class);
		RoleResourceModel roleSource = null;
		for(RoleModel role : list){
			roleSource = new RoleResourceModel();
			roleSource.setR_id(role.getR_id());
			roleresourceService.deleteRoleResource(roleSource);
			role.setStatus("0");
			roleService.updateRole(role);
		}	
		return null;
	}

	public String list() {
		
		
		model.setStatus("1");
		int count = roleService.getRoleCount(model);
		modelList = roleService.getRoleList(start,limit,model);
		JSONObject obj = new JSONObject();
		JSONArray jsonusers = JSONArray.fromObject(modelList);
		obj.put("totalCount", count);
		obj.put("roots", jsonusers);
		log.debug("角色列表："+obj);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(obj.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//----------------------------------------------------------------------
	
	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}
	
	public void setRoleresourceService(RoleResourceServiceImpl roleresourceService) {
		this.roleresourceService = roleresourceService;
	}

	public List<RoleModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<RoleModel> modelList) {
		this.modelList = modelList;
	}
	
	public void setModel(RoleModel model) {
		this.model = model;
	}

	public RoleModel getModel() {

		return model;
	}	
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void prepare() throws Exception {
		model = new RoleModel();
		modelList = new ArrayList<RoleModel>();
	}
	
}


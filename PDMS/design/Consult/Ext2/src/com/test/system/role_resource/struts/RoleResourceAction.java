package com.test.system.role_resource.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.test.system.resource.model.ResourceModel;
import com.test.system.resource.service.ResourceServiceImpl;
import com.test.system.role.model.RoleModel;
import com.test.system.role.service.RoleServiceImpl;
import com.test.system.role_resource.model.RoleResourceModel;
import com.test.system.role_resource.service.RoleResourceServiceImpl;
import com.test.system.user.model.UserModel;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class RoleResourceAction extends ActionSupport implements ModelDriven<RoleResourceModel>,Preparable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private RoleResourceModel model;
	
	private List<RoleResourceModel> modelList; 
	
	private String node;
	
	private String checkedIds;
	
	private RoleResourceServiceImpl roleresourceService;
	
	private ResourceServiceImpl resourceService;
	
	private RoleServiceImpl roleService;
	
	public String getTree(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserModel user = (UserModel) session.getAttribute("login_user");
		List<ResourceModel> resourceList = null;
		if(user!=null){
			String usertype = user.getU_type();
			if("-1".equals(usertype)){//超级用户
				ResourceModel resource = new ResourceModel();
				resource.setP_rid(this.node);
				resource.setStatus("1");
				resourceList = resourceService.getResourceList(resource);
			}else{
				ResourceModel resource = new ResourceModel();
				resource.setU_id(user.getU_id());
				resource.setP_rid(this.node);
				resource.setStatus("1");
				resourceList = resourceService.getUserResourceList(resource);
			}
			List<JSONObject> jsonList = new ArrayList<JSONObject>();
			JSONObject node = null;
			for(ResourceModel temp : resourceList){
				model.setS_id(temp.getR_id());
				int count = roleresourceService.getRoleResourceCount(model);
				node = new JSONObject();
				node.put("id", temp.getR_id());
				node.put("text", temp.getR_name());
				node.put("r_desc", temp.getR_desc());
				node.put("link", temp.getLinkaddress());
				if("2".equals(temp.getR_type())){
					node.put("leaf", true);
				}else{
					node.put("cls", "folder");
				}
				if(count==0){
					node.put("checked", false);
				}else{
					node.put("checked", true);
				}
				jsonList.add(node);
			}
			JSONArray json = JSONArray.fromObject(jsonList);
			log.debug("获取资源树为："+json);
			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse().getWriter();
				out.print(json.toString());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String update() {
		
		try {
			if(this.checkedIds!=null){
				RoleModel role = new RoleModel();
				role.setR_id(model.getR_id());
				role.setStatus("1");
				if(roleService.getRoleCount(role)>0){//存在角色
					roleresourceService.deleteRoleResource(model); 
					String[] sources = this.checkedIds.split(",");
					for(String temp : sources){
						model.setS_id(temp);
						roleresourceService.createRoleResource(model);
					}
				}
			}
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			json.put("success", "1");
			out.print(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	//----------------------------------------------------------------------
	
	public void setRoleresourceService(RoleResourceServiceImpl roleresourceService) {
		this.roleresourceService = roleresourceService;
	}
	
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public void setResourceService(ResourceServiceImpl resourceService) {
		this.resourceService = resourceService;
	}

	public void setRoleService(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}

	public List<RoleResourceModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<RoleResourceModel> modelList) {
		this.modelList = modelList;
	}
	
	public void setModel(RoleResourceModel model) {
		this.model = model;
	}

	public RoleResourceModel getModel() {

		return model;
	}	
	
	public void prepare() throws Exception {
		model = new RoleResourceModel();
		modelList = new ArrayList<RoleResourceModel>();
	}
	
}


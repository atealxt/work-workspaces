package com.test.system.resource.struts;

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
public class ResourceAction extends ActionSupport implements ModelDriven<ResourceModel>,Preparable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private ResourceModel model;
	
	private String node;
	
	private List<ResourceModel> modelList; 
	
	private ResourceServiceImpl resourceService;
	
	private RoleResourceServiceImpl roleresourceService;
	
	public String create(){
		
		try {
			model.setStatus("1");
			int count = resourceService.createResource(model);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			if(count==1){
				json.put("success", "1");
			}else{
				json.put("success", "0");
			}
			out.print(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	public String update() {

		try {
			int count = resourceService.updateResource(model);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			if(count==1){
				json.put("success", "1");
			}else{
				json.put("success", "0");
			}
			out.print(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	public String delete() {
		
		try {
			model.setStatus("2");
			int count = resourceService.updateResource(model);
			RoleResourceModel roleResource = new RoleResourceModel();
			roleResource.setS_id(model.getR_id());
			roleresourceService.deleteRoleResource(roleResource);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			if(count==1){
				json.put("success", "1");
			}else{
				json.put("success", "0");
			}
			out.print(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	public String getTree(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserModel user = (UserModel) session.getAttribute("login_user");
		if(user!=null){ 
			String usertype = user.getU_type();
			ResourceModel resource = new ResourceModel();
			resource.setP_rid(this.node);
			resource.setStatus("1");
			if("-1".equals(usertype)){//超级用户
				modelList = resourceService.getResourceList(resource);
			}else{
				resource.setU_id(user.getU_id());
				//modelList = resourceService.getUserResourceList(resource);
				modelList = resourceService.getResourceList(resource);
			}
			List<JSONObject> jsonList = new ArrayList<JSONObject>();
			JSONObject node = null;
			for(ResourceModel temp : modelList){
				node = new JSONObject();
				node.put("id", temp.getR_id());
				node.put("text", temp.getR_name());
				node.put("r_desc", temp.getR_desc());
				node.put("r_type", temp.getR_type());
				node.put("link", temp.getLinkaddress());
				if("2".equals(temp.getR_type())){
					node.put("leaf", true);
				}else{
					node.put("cls", "folder");
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
	
	public String updateOrderBy(){
		
		try {
			String updateJson = ServletActionContext.getRequest().getParameter("updateJson");
			JSONArray ja = JSONArray.fromObject(updateJson);
			List<ResourceModel> mlist = (ArrayList<ResourceModel>) JSONArray.toCollection(ja, ResourceModel.class);
			for(ResourceModel temp : mlist){
				resourceService.updateResource(temp);
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
	
	public void setResourceService(ResourceServiceImpl resourceService) {
		this.resourceService = resourceService;
	}
	
	public void setRoleresourceService(RoleResourceServiceImpl roleresourceService) {
		this.roleresourceService = roleresourceService;
	}

	public List<ResourceModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<ResourceModel> modelList) {
		this.modelList = modelList;
	}
	
	public void setModel(ResourceModel model) {
		this.model = model;
	}

	public ResourceModel getModel() {

		return model;
	}	
	
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public void prepare() throws Exception {
		model = new ResourceModel();
		modelList = new ArrayList<ResourceModel>();
	}
	
}


package com.test.system.user.struts;

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
import com.test.system.user.model.UserModel;
import com.test.system.user.service.UserServiceImpl;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class UserAction extends ActionSupport implements ModelDriven<UserModel>,Preparable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private UserModel model;
	
	private String jsonData;
	
	private int start;
	
	private int limit;
	
	private List<UserModel> modelList; 
	
	private UserServiceImpl userService;
	
	public String create(){
		
		model.setStatus("1");
		int result = userService.createUser(model);
		return null;  
	}
	
	public String update() {

		int result = userService.updateUser(model);
		return null;
	}

	public String delete() {
		
		log.debug(jsonData);
		JSONArray json = JSONArray.fromObject(jsonData);
		List<UserModel> list = (ArrayList<UserModel>) JSONArray.toCollection(json, UserModel.class);
		for(UserModel user : list){
			log.debug(user.getU_id());
			userService.deleteUser(user);
		}		
		return null;
	}

	public String doLogin(){
		
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			if(model.getLoginname()!=null&&model.getPassword()!=null){
				int count = userService.getUserCount(model);
				if(count==1){
					HttpSession session = ServletActionContext.getRequest().getSession();
					model = userService.getUser(model);
					session.setAttribute("login_user", model);
					json.put("success", "1");
				}else{
					json.put("success", "0");
				}
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
	
	public String checkLogin(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserModel user = (UserModel) session.getAttribute("login_user");
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			if(user!=null){
				json.put("timeout", "yes");
			}else{
				json.put("timeout", "no");
			}
			out.print(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public String list() {
		
		model.setStatus("1");
		if(model.getUsername()!=null&&!"".equals(model.getUsername())){
			model.setUsername("%"+model.getUsername()+"%");
		}
		int count = userService.getUserCount(model);
		modelList = userService.getUserList(start,limit,model);
		JSONObject obj = new JSONObject();
		JSONArray jsonusers = JSONArray.fromObject(modelList);
		obj.put("totalCount", count);
		obj.put("roots", jsonusers);
		log.debug("用户列表："+obj);
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
	
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	public List<UserModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<UserModel> modelList) {
		this.modelList = modelList;
	}
	
	public void setModel(UserModel model) {
		this.model = model;
	}

	public UserModel getModel() {

		return model;
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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

	public void prepare() throws Exception {
		model = new UserModel();
		modelList = new ArrayList<UserModel>();
	}
	
}


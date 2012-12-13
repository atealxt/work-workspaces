package com.test.system.user.model;

import java.io.Serializable;

 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class UserModel implements Serializable{

	    private String u_id;
    	
	    private String g_id;
    	
	    private String loginname;
    	
	    private String password;
    	
	    private String username;
    	
	    private String mobilephone;
    	
	    private String email;
    	
	    private String createtime;
    	
	    private String u_type;
    	
	    private String lastlogintime;
    	
	    private Integer logincount;
    	
	    private String status;
    	
	
			
	public String getU_id(){
		return this.u_id;
	}
	
	public void setU_id(String u_id){
		this.u_id = u_id;
	}
    	
			
	public String getG_id(){
		return this.g_id;
	}
	
	public void setG_id(String g_id){
		this.g_id = g_id;
	}
    	
			
	public String getLoginname(){
		return this.loginname;
	}
	
	public void setLoginname(String loginname){
		this.loginname = loginname;
	}
    	
			
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
    	
			
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
    	
			
	public String getMobilephone(){
		return this.mobilephone;
	}
	
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
    	
			
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
    	
			
	public String getCreatetime(){
		return this.createtime;
	}
	
	public void setCreatetime(String createtime){
		this.createtime = createtime;
	}
    	
			
	public String getU_type(){
		return this.u_type;
	}
	
	public void setU_type(String u_type){
		this.u_type = u_type;
	}
    	
			
	public String getLastlogintime(){
		return this.lastlogintime;
	}
	
	public void setLastlogintime(String lastlogintime){
		this.lastlogintime = lastlogintime;
	}
    	
			
	public Integer getLogincount(){
		return this.logincount;
	}
	
	public void setLogincount(Integer logincount){
		this.logincount = logincount;
	}
    	
			
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
    	
	
	public String toString(){
		StringBuffer sb = new StringBuffer("UserModel={\n");
						sb.append("u_id="+this.u_id+",\n");
								sb.append("g_id="+this.g_id+",\n");
								sb.append("loginname="+this.loginname+",\n");
								sb.append("password="+this.password+",\n");
								sb.append("username="+this.username+",\n");
								sb.append("mobilephone="+this.mobilephone+",\n");
								sb.append("email="+this.email+",\n");
								sb.append("createtime="+this.createtime+",\n");
								sb.append("u_type="+this.u_type+",\n");
								sb.append("lastlogintime="+this.lastlogintime+",\n");
								sb.append("logincount="+this.logincount+",\n");
								sb.append("status="+this.status+"\n}\n");
						return sb.toString();
	}
	
	
}


package com.test.system.group.model;

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
public class GroupModel implements Serializable{

	    private String g_id;
    	
	    private String g_name;
    	
	    private String p_gid;
    	
	    private String createtime;
    	
	    private String g_desc;
    	
	    private String g_type;
    	
	    private String status;
    	
	
			
	public String getG_id(){
		return this.g_id;
	}
	
	public void setG_id(String g_id){
		this.g_id = g_id;
	}
    	
			
	public String getG_name(){
		return this.g_name;
	}
	
	public void setG_name(String g_name){
		this.g_name = g_name;
	}
    	
			
	public String getP_gid(){
		return this.p_gid;
	}
	
	public void setP_gid(String p_gid){
		this.p_gid = p_gid;
	}
    	
			
	public String getCreatetime(){
		return this.createtime;
	}
	
	public void setCreatetime(String createtime){
		this.createtime = createtime;
	}
    	
			
	public String getG_desc(){
		return this.g_desc;
	}
	
	public void setG_desc(String g_desc){
		this.g_desc = g_desc;
	}
    	
			
	public String getG_type(){
		return this.g_type;
	}
	
	public void setG_type(String g_type){
		this.g_type = g_type;
	}
    	
			
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
    	
	
	public String toString(){
		StringBuffer sb = new StringBuffer("GroupModel={\n");
						sb.append("g_id="+this.g_id+",\n");
								sb.append("g_name="+this.g_name+",\n");
								sb.append("p_gid="+this.p_gid+",\n");
								sb.append("createtime="+this.createtime+",\n");
								sb.append("g_desc="+this.g_desc+",\n");
								sb.append("g_type="+this.g_type+",\n");
								sb.append("status="+this.status+"\n}\n");
						return sb.toString();
	}
	
	
}


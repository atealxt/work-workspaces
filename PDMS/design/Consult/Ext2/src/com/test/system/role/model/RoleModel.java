package com.test.system.role.model;

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
public class RoleModel implements Serializable{

	    private String r_id;
    	
	    private String r_name;
    	
	    private String createtime;
    	
	    private String r_desc;
    	
	    private String status;
    	
	
			
	public String getR_id(){
		return this.r_id;
	}
	
	public void setR_id(String r_id){
		this.r_id = r_id;
	}
    	
			
	public String getR_name(){
		return this.r_name;
	}
	
	public void setR_name(String r_name){
		this.r_name = r_name;
	}
    	
			
	public String getCreatetime(){
		return this.createtime;
	}
	
	public void setCreatetime(String createtime){
		this.createtime = createtime;
	}
    	
			
	public String getR_desc(){
		return this.r_desc;
	}
	
	public void setR_desc(String r_desc){
		this.r_desc = r_desc;
	}
    	
			
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
    	
	
	public String toString(){
		StringBuffer sb = new StringBuffer("RoleModel={\n");
						sb.append("r_id="+this.r_id+",\n");
								sb.append("r_name="+this.r_name+",\n");
								sb.append("createtime="+this.createtime+",\n");
								sb.append("r_desc="+this.r_desc+",\n");
								sb.append("status="+this.status+"\n}\n");
						return sb.toString();
	}
	
	
}


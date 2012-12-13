package com.test.system.role_resource.model;

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
public class RoleResourceModel implements Serializable{

	    private String id;
    	
	    private String r_id;
    	
	    private String s_id;
    	
	
			
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
    	
			
	public String getR_id(){
		return this.r_id;
	}
	
	public void setR_id(String r_id){
		this.r_id = r_id;
	}
    	
			
	public String getS_id(){
		return this.s_id;
	}
	
	public void setS_id(String s_id){
		this.s_id = s_id;
	}
    	
	
	public String toString(){
		StringBuffer sb = new StringBuffer("RoleResourceModel={\n");
						sb.append("id="+this.id+",\n");
								sb.append("r_id="+this.r_id+",\n");
								sb.append("s_id="+this.s_id+"\n}\n");
						return sb.toString();
	}
	
	
}


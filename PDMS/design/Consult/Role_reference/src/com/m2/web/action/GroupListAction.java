package com.m2.web.action;

import java.util.HashMap;
import java.util.Map;
import com.m2.common.Constant;
import com.m2.common.Util;
import com.m2.entity.Group;

public class GroupListAction  extends BasePagination{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8078590948370080086L;
	
	private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Class   getClazz(){
		return Group.class;
	}
	
	public Map getConditions(){
        Map<String[],Object> conditions = new HashMap<String[],Object>();
		if(Util.isNotBlank(this.getName())){			
			String[] arr = new String[]{"name",Constant.CONDITION_EQUAL };
			conditions.put(arr, this.getName());
		}
		return conditions;		
	
	}
		
}

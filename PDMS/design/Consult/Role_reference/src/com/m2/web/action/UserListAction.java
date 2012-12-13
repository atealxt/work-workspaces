package com.m2.web.action;

import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import com.m2.common.Constant;
import com.m2.common.Util;
import com.m2.entity.User;


public class UserListAction extends BasePagination{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7726720601834115798L;

	private String realName;
	
	private String loginName;
	
	
	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}
	



	public Class   getClazz(){
		return User.class;
	}
	
	
	public Map getConditions(){
        Map<String[],Object> conditions = new HashMap<String[],Object>();
		if(Util.isNotBlank(this.getLoginName())){			
			String[] arr = new String[]{"loginName",Constant.CONDITION_EQUAL };
			conditions.put(arr, this.getLoginName());
		}
		if(Util.isNotBlank(this.getRealName())){			
			String[] arr = new String[]{"realName",Constant.CONDITION_EQUAL };
			conditions.put(arr, this.getRealName());
		}		

		return conditions;		
	
	}
	
	
	
	
	

}

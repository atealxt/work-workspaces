package com.m2.web.interceptor;

import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.m2.web.UserSession;
import com.m2.common.Constant;


public class UserPermissionInterceptor  extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8981730354012224201L;

	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext ac = invocation.getInvocationContext();
		String actionName = "/"+ac.getName();
		String paramAction="index";
		
		Map map = ac.getParameters();
		String[] _action = (String[]) map.get("action");
		if (_action != null) {
			paramAction = _action[0];
		}
		if ((paramAction==null)||("".equalsIgnoreCase(paramAction))) paramAction="index";
		if (ac.getSession().get(Constant.USER_SESSION)==null){
			ac.getValueStack().set("interceptError", "You should login in first.");
			return "login";
		}
			
		
		UserSession us = (UserSession) (ac.getSession().get(Constant.USER_SESSION));
		Set resources = us.getResources();
		String currentResource = actionName +"?action="+paramAction;
		if ((resources.contains(currentResource))||("YT000".equals(us.getLoginName()))){
			return invocation.invoke();
			
		}else{
			ac.getValueStack().set("error", "You have no permission.");
			ac.getValueStack().set("actionError", "You have no permission.");
			return "syserror";
		}
		
		
		
	}
	
	

}

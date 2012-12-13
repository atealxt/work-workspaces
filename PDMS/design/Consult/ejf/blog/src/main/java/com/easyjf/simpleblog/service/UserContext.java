package com.easyjf.simpleblog.service;

import com.easyjf.simpleblog.domain.User;
import com.easyjf.web.ActionContext;

public class UserContext {
	public static User getCurrentUser() {
		return (User)ActionContext.getContext().getSession().getAttribute("user");
	}
	public static boolean isAdmin()
	{
		return ActionContext.getContext().getSession().getAttribute("ADMIN")!=null;
	}	
}

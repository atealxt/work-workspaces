package com.easyjf.simpleblog.mvc;

import com.easyjf.simpleblog.service.UserContext;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.core.AbstractPageCmdAction;

public abstract class BaseAction extends AbstractPageCmdAction {
	@Override
	public Object doBefore(WebForm form, Module module) {
		if (!UserContext.isAdmin()) {
			return new Page("login", "login.html", "html");
		}
		return super.doBefore(form, module);
	}

}

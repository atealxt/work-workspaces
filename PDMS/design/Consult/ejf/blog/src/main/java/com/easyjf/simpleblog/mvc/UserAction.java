package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.User;
import com.easyjf.simpleblog.service.IUserService;
import com.easyjf.util.CommUtil;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.tools.IPageList;


/**
 * UserAction
 * @author EasyJWeb 1.0-m2
 * $Id: UserAction.java,v 0.0.1 2008-1-17 16:01:38 EasyJWeb 1.0-m3 with ExtJS Exp $
 */
@Action
public class UserAction extends BaseAction {
	
	@Inject
	private IUserService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(IUserService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getUserBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delUser(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		User object = form.toPo(User.class);
		if (!hasErrors())
			service.addUser(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		User object = service.getUser(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateUser(id, object);
		return pageForExtForm(form);
	}
}
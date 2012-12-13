package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.Link;
import com.easyjf.simpleblog.service.ILinkService;
import com.easyjf.util.CommUtil;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.tools.IPageList;


/**
 * LinkAction
 * @author EasyJWeb 1.0-m2
 * $Id: LinkAction.java,v 0.0.1 2008-1-17 15:44:24 EasyJWeb 1.0-m3 with ExtJS Exp $
 */
@Action
public class LinkAction extends BaseAction {
	
	@Inject
	private ILinkService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(ILinkService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getLinkBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delLink(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Link object = form.toPo(Link.class);
		if (!hasErrors())
			service.addLink(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Link object = service.getLink(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateLink(id, object);
		return pageForExtForm(form);
	}
}
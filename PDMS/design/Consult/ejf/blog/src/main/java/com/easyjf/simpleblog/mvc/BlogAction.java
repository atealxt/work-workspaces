package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.simpleblog.domain.Blog;
import com.easyjf.simpleblog.service.IBlogService;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;

/**
 * BlogAction
 * 
 * @author EasyJWeb 1.0-m2 $Id: BlogAction.java,v 0.0.1 2008-1-17 18:22:58
 *         EasyJWeb 1.0-m3 with ExtJS Exp $
 */
@Action
public class BlogAction extends BaseAction {

	@Inject
	private IBlogService service;

	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doRead(WebForm form) {
		Blog blog = service.getBlog();
		form.jsonResult(blog);
		return pageForExtForm(form);
	}

	public Page doUpdate(WebForm form) {
		Blog object = service.getBlog();
		form.toPo(object, true);
		if (!hasErrors())
			service.updateBlog(object);
		return pageForExtForm(form);
	}

	/*
	 * set the current service return service
	 */
	public void setService(IBlogService service) {
		this.service = service;
	}
}
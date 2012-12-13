package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.Comment;
import com.easyjf.simpleblog.service.ICommentService;
import com.easyjf.util.CommUtil;
import com.easyjf.web.ActionContext;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.tools.IPageList;


/**
 * CommentAction
 * @author EasyJWeb 1.0-m2
 * $Id: CommentAction.java,v 0.0.1 2008-1-17 16:16:39 EasyJWeb 1.0-m3 with ExtJS Exp $
 */
@Action
public class CommentAction extends BaseAction {
	
	@Inject
	private ICommentService service;
	/*
	 * set the current service
	 * return service
	 */
	public void setService(ICommentService service) {
		this.service = service;
	}
	
	public Page doIndex(WebForm f, Module m) {
		return page("list");
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(QueryObject.class);
		IPageList pageList = service.getCommentBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delComment(id);
		return pageForExtForm(form);
	}

	public Page doSave(WebForm form) {
		Comment object = form.toPo(Comment.class);
		object.setIp(ActionContext.getContext().getRequest().getRemoteAddr());
		if (!hasErrors())
			service.addComment(object);
		return pageForExtForm(form);
	}
	
	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Comment object = service.getComment(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateComment(id, object);
		return pageForExtForm(form);
	}
}
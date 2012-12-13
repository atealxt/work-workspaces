package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.simpleblog.domain.Topic;
import com.easyjf.simpleblog.query.TopicQuery;
import com.easyjf.simpleblog.service.ITopicService;
import com.easyjf.util.CommUtil;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.tools.IPageList;

/**
 * TopicAction
 * 
 * @author EasyJWeb 1.0-m2 $Id: TopicAction.java,v 0.0.1 2008-1-15 9:40:33
 *         EasyJWeb 1.0-m2 Exp $
 */
@Action
public class TopicAction extends BaseAction {
	@Inject
	private ITopicService service;

	public void setService(ITopicService service) {
		this.service = service;
	}

	public Page doSave(WebForm form) {
		Topic t = form.toPo(Topic.class);		
		if (!this.hasErrors()) {
			this.service.addTopic(t);
		}
		return this.pageForExtForm(form);
	}

	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Topic object = service.getTopic(id);
		form.toPo(object, true);
		if (!hasErrors())
			service.updateTopic(id, object);
		return pageForExtForm(form);
	}

	public Page doRemove(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delTopic(id);
		return pageForExtForm(form);
	}

	public Page doList(WebForm form) {		
		TopicQuery qo = form.toPo(TopicQuery.class);
		IPageList pageList = this.service.getTopicBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
}
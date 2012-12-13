package com.easyjf.simpleblog.mvc;

import com.easyjf.web.Page;

public class ManageAction extends BaseAction {
	public Page doScript() {
		Page p = new Page("manageJs", "/manage/manage.js", "template");
		p.setContentType("js");
		return p;
	}
}

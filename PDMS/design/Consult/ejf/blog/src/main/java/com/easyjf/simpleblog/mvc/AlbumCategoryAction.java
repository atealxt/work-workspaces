package com.easyjf.simpleblog.mvc;

import java.util.List;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.AlbumCategory;
import com.easyjf.simpleblog.service.IAlbumCategoryService;
import com.easyjf.util.CommUtil;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.ajax.AjaxUtil;
import com.easyjf.web.tools.IPageList;

/**
 * AlbumCategoryAction
 * 
 * @author EasyJWeb 1.0-m2 $Id: AlbumCategoryAction.java,v 0.0.1 2008-1-15
 *         9:40:03 EasyJWeb 1.0-m2 Exp $
 */
@Action
public class AlbumCategoryAction extends BaseAction {
	@Inject
	private IAlbumCategoryService service;

	public void setService(IAlbumCategoryService service) {
		this.service = service;
	}

	public Page doGetCategory(WebForm form) {
		String id = CommUtil.null2String(form.get("id"));
		QueryObject query = new QueryObject();
		query.setPageSize(-1);
		if (!"".equals(id)) {
			AlbumCategory parent = this.service.getAlbumCategory(new Long(id));
			query.addQuery("obj.parent", parent, "=");
		} else {
			query.addQuery("obj.parent is EMPTY", null);
		}
		IPageList pageList = this.service.getAlbumCategoryBy(query);
		String treeData = CommUtil.null2String(form.get("treeData"));
		if ("".equals(treeData)) {// 获得pageList的数组
			if (pageList.getRowCount() > 0) {
				for (int i = 0; i < pageList.getResult().size(); i++) {
					AlbumCategory category = (AlbumCategory) pageList
							.getResult().get(i);
					pageList.getResult().set(
							i,
							new AlbumCategory(category.getId(), category
									.getName(), category.getIntro()));
				}
			}
			form.addResult("json", AjaxUtil.getJSON(pageList));
		} else {
			List<Node> nodes = new java.util.ArrayList<Node>();
			if (pageList.getRowCount() > 0) {
				for (int i = 0; i < pageList.getResult().size(); i++) {
					AlbumCategory category = (AlbumCategory) pageList
							.getResult().get(i);
					nodes.add(new Node(category));
				}
			} else {
				AlbumCategory c = new AlbumCategory();
				c.setName("无分类");
				c.setId(0l);
				nodes.add(new Node(c));
			}
			form.addResult("json", AjaxUtil.getJSON(nodes));
		}
		return Page.JSONPage;
	}

	private class Node {
		private AlbumCategory category;

		Node(AlbumCategory category) {
			this.category = category;
		}

		public String getId() {
			return category.getId().toString();
		}

		public boolean getLeaf() {
			return category.getChildren().size() < 1;
		}

		public String getText() {
			return category.getName();
		}

		public String getQtip() {
			return category.getName();
		}
	}
}
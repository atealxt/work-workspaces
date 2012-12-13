package com.easyjf.simpleblog.query;

import com.easyjf.container.annonation.POLoad;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.AlbumCategory;

public class AlbumQuery extends QueryObject {
	@POLoad(name="categoryId")
	private AlbumCategory category;
	private String title="";
	public AlbumCategory getCategory() {
		return category;
	}
	public void setCategory(AlbumCategory category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void customizeQuery() {
		if(category!=null)
		{
			this.addQuery("obj.category",category,"=");
		}
		if(!"".equals(title))
		{
			this.addQuery("obj.title","%"+title+"%","like");
		}
		if(this.getOrderBy()==null|| "".equals(this.getOrderBy()))
		{
			this.setOrderBy("inputTime");
			this.setOrderType("desc");
		}
		super.customizeQuery();
	}
}

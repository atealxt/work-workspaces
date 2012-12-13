package com.easyjf.simpleblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;

@FormPO(name = "链接", disInject = "id")
@Entity
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Field(name = "标题")
	private String title;
	@Field(name = "URL")
	private String url;
	@Field(name = "RSS")
	private String rss;
	@Field(name = "简介")
	private String intro;

	public Long getId() {
		return id;
	}

	public String getIntro() {
		return intro;
	}

	public String getRss() {
		return rss;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setRss(String rss) {
		this.rss = rss;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

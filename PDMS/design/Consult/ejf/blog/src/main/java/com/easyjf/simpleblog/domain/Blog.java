package com.easyjf.simpleblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;

@FormPO(name = "Blog基本信息", disInject = "id,author,password,hits")
@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Field(name = "标题")
	private String title;
	@Field(name = "简介")
	private String intro;
	@Field(name = "用户名")
	private String author;
	@Field(name = "管理员密码")
	private String password;
	
	@Field(name="域名")
	private String domain;
	
	@Field(name = "联系邮箱")
	private String email;
	@Field(name = "使用皮肤")
	private String theme;
	@Field(name = "公告")
	private String announce;
	@Field(name = "页首自定义")
	private String header;
	@Field(name = "页脚自定义")
	private String footer;
	@Field(name = "关键字")
	private String keywords;
	@Field(name = "访问量")
	private Integer hits = 0;

	public String getAnnounce() {
		return announce;
	}

	public String getAuthor() {
		return author;
	}

	public String getEmail() {
		return email;
	}

	public String getFooter() {
		return footer;
	}

	public String getHeader() {
		return header;
	}

	public Integer getHits() {
		return hits;
	}

	public Long getId() {
		return id;
	}

	public String getIntro() {
		return intro;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getPassword() {
		return password;
	}

	public String getTitle() {
		return title;
	}

	public void setAnnounce(String announce) {
		this.announce = announce;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}

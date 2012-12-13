package com.easyjf.simpleblog.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;

@FormPO(name = "评论", inject = "content,inputTime,ip,name,email,url")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob
	@Field(name = "内容")
	private String content;	
	 @ManyToOne
	 @Field(name = "用户")
	 private User user;
	@Field(gener = true)
	private Date inputTime = new Date();
	@Field(gener = true)
	private String ip;
	@Field(name = "状态")
	private Integer status;

	public String getContent() {
		return content;
	}

	public Long getId() {
		return id;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public String getIp() {
		return ip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

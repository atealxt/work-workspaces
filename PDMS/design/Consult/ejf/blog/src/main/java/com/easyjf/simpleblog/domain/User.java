package com.easyjf.simpleblog.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.Validator;

@FormPO(name = "用户", disInject = "id,lastLoginTime,loginTimes,comments,status")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Field(name="用户名",validator=@Validator(name="string",value="min:2;max:36",required=true))
	private String name;
	@Field(name = "密码")
	private String password;
	@Field(name = "电子邮箱")
	private String email;
	@Field(name = "备注")
	private String remark;
	@Field(name = "个人照片")
	private String pic;
	@Field(gener = false)
	private Date lastLoginTime;
	@Field(gener = false)
	private Integer loginTimes = 0;
	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new java.util.ArrayList<Comment>();
	@Field(name = "状态")
	private Integer status;

//	public List<Comment> getComments() {
//		return comments;
//	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getPic() {
		return pic;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getStatus() {
		return status;
	}

//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}

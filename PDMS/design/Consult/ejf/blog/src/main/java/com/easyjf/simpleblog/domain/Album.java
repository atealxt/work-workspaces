package com.easyjf.simpleblog.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
import com.easyjf.container.annonation.Validator;
@FormPO(name="相册",disInject="id,comments,inputTime,readTimes")
@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Field(name="标题",validator=@Validator(name="string",value="min:2;max:100",required=true))
	private String title;
	
	@Column(length=1000)
	private String intro;
	private String path;
	private Date inputTime=new Date();
	private Integer readTimes=0;
	@POLoad
	@ManyToOne
	private AlbumCategory category;
	@OneToMany(mappedBy="album")
	private List<AlbumComment> comments = new java.util.ArrayList<AlbumComment>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public AlbumCategory getCategory() {
		return category;
	}
	public void setCategory(AlbumCategory category) {
		this.category = category;
	}
	public Integer getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	public List<AlbumComment> getComments() {
		return comments;
	}
	public void setComments(List<AlbumComment> comments) {
		this.comments = comments;
	}

}

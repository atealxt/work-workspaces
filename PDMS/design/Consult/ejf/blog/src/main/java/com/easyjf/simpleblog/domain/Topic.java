package com.easyjf.simpleblog.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
import com.easyjf.container.annonation.Validator;

@FormPO(name = "文章", disInject = "id,comments,inputTime,readTimes")
@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Field(name="标题",validator=@Validator(name="string",value="min:2;max:100",required=true))
	private String title;	
	@Lob
	private String content;
	private String intro;
	
	@POLoad
	@ManyToOne
	private TopicCategory category;
	
	@OneToMany(mappedBy = "topic")
	private List<TopicComment> comments = new java.util.ArrayList<TopicComment>();
	private Date inputTime = new Date();
	private Integer readTimes = 0;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public TopicCategory getCategory() {
		return category;
	}

	public void setCategory(TopicCategory category) {
		this.category = category;
	}

	public List<TopicComment> getComments() {
		return comments;
	}

	public void setComments(List<TopicComment> comments) {
		this.comments = comments;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
}

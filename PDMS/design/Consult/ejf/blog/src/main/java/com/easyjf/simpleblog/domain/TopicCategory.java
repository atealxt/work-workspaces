package com.easyjf.simpleblog.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
import com.easyjf.container.annonation.Validator;
@FormPO(name = "文章分类", disInject = "id,topics,children")
@Entity
public class TopicCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Field(name = "文章分类", validator = @Validator(name = "string", required = true))
	private String name;
	
	@Column(length=1000)
	private String intro;
	
	@POLoad(name="parentId")
	@ManyToOne
	private TopicCategory parent;
	
	@OneToMany(mappedBy="parent")
	private List<TopicCategory> children = new java.util.ArrayList<TopicCategory>();
	
	@OneToMany(mappedBy="category")
	@OrderBy("inputTime desc")
	private List<Topic> topics = new java.util.ArrayList<Topic>();
	public TopicCategory()
	{
		
	}
	public TopicCategory(Long id,String name,String intro)
	{
		this.id=id;
		this.name=name;
		this.intro=intro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public TopicCategory getParent() {
		return parent;
	}
	public void setParent(TopicCategory parent) {
		this.parent = parent;
	}
	public List<TopicCategory> getChildren() {
		return children;
	}
	public void setChildren(List<TopicCategory> children) {
		this.children = children;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public Topic getLastTopic()
	{
		if(this.topics.size()>0)return this.topics.get(0);
		else return null;
	}
}

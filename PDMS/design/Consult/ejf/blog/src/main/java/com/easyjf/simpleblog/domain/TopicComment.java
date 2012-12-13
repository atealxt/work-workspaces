package com.easyjf.simpleblog.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
@FormPO(name="文章评论",inject="topic")
@Entity
public class TopicComment extends Comment {
	@POLoad(name="topicId")
	@ManyToOne
	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}	
}

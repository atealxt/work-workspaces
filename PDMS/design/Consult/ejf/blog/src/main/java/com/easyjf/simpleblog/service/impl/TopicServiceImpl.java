package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.web.tools.IPageList;
import com.easyjf.simpleblog.domain.Topic;
import com.easyjf.simpleblog.service.ITopicService;
import com.easyjf.simpleblog.service.LogicException;
import com.easyjf.simpleblog.dao.ITopicDAO;


/**
 * TopicServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: TopicServiceImpl.java,v 0.0.1 2008-1-15 9:40:33 EasyJWeb 1.0-m2 Exp $
 */
public class TopicServiceImpl implements ITopicService{
	
	private ITopicDAO topicDao;
	
	public void setTopicDao(ITopicDAO topicDao){
		this.topicDao=topicDao;
	}
	
	public Long addTopic(Topic topic) {	
		this.topicDao.save(topic);
		if (topic != null && topic.getId() != null) {
			return topic.getId();
		}
		return null;
	}
	
	public Topic getTopic(Long id) {
		Topic topic = this.topicDao.get(id);
		return topic;
		}
	
	public boolean delTopic(Long id) {	
			Topic topic = this.getTopic(id);
			if(topic.getComments().size()>0)throw new LogicException("该文章下还有评论，不能删除！");
			if (topic != null) {
				this.topicDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelTopics(List<Serializable> topicIds) {
		
		for (Serializable id : topicIds) {
			delTopic((Long) id);
		}
		return true;
	}
	
	public IPageList getTopicBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Topic.class,this.topicDao);		
	}
	
	public boolean updateTopic(Long id, Topic topic) {
		if (id != null) {
			topic.setId(id);
		} else {
			return false;
		}
		this.topicDao.update(topic);
		return true;
	}	
	
}

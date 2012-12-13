package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.simpleblog.dao.ITopicCategoryDAO;
import com.easyjf.simpleblog.domain.TopicCategory;
import com.easyjf.simpleblog.service.ITopicCategoryService;
import com.easyjf.simpleblog.service.LogicException;
import com.easyjf.simpleblog.service.UserContext;
import com.easyjf.web.tools.IPageList;


/**
 * TopicCategoryServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: TopicCategoryServiceImpl.java,v 0.0.1 2008-1-15 9:40:40 EasyJWeb 1.0-m2 Exp $
 */
public class TopicCategoryServiceImpl implements ITopicCategoryService{
	
	private ITopicCategoryDAO topicCategoryDao;
	
	public void setTopicCategoryDao(ITopicCategoryDAO topicCategoryDao){
		this.topicCategoryDao=topicCategoryDao;
	}
	
	public Long addTopicCategory(TopicCategory topicCategory) {	
		this.topicCategoryDao.save(topicCategory);
		if (topicCategory != null && topicCategory.getId() != null) {
			return topicCategory.getId();
		}
		return null;
	}
	
	public TopicCategory getTopicCategory(Long id) {
		TopicCategory topicCategory = this.topicCategoryDao.get(id);
		return topicCategory;
		}
	
	public boolean delTopicCategory(Long id) {
		if(!UserContext.isAdmin())throw new LogicException("无权限");		
			TopicCategory topicCategory = this.getTopicCategory(id);
			if(topicCategory.getTopics().size()>0)throw new LogicException("分类"+topicCategory.getName()+"下还有文章，不能删除！");
			if (topicCategory != null) {
				for(TopicCategory c:topicCategory.getChildren())
				{
					this.delTopicCategory(c.getId());
				}
				this.topicCategoryDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelTopicCategorys(List<Serializable> topicCategoryIds) {		 
		for (Serializable id : topicCategoryIds) {
			delTopicCategory((Long) id);
		}
		return true;
	}
	
	public IPageList getTopicCategoryBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, TopicCategory.class,this.topicCategoryDao);		
	}
	
	public boolean updateTopicCategory(Long id, TopicCategory topicCategory) {
		if (id != null) {
			topicCategory.setId(id);
		} else {
			return false;
		}
		this.topicCategoryDao.update(topicCategory);
		return true;
	}	
	
}

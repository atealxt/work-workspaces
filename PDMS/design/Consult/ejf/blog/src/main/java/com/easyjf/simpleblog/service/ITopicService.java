package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.Topic;
/**
 * TopicService
 * @author EasyJWeb 1.0-m2
 * $Id: TopicService.java,v 0.0.1 2008-1-15 9:40:33 EasyJWeb 1.0-m2 Exp $
 */
public interface ITopicService {
	/**
	 * 保存一个Topic，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addTopic(Topic instance);
	
	/**
	 * 根据一个ID得到Topic
	 * 
	 * @param id
	 * @return
	 */
	Topic getTopic(Long id);
	
	/**
	 * 删除一个Topic
	 * @param id
	 * @return
	 */
	boolean delTopic(Long id);
	
	/**
	 * 批量删除Topic
	 * @param ids
	 * @return
	 */
	boolean batchDelTopics(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Topic
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getTopicBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Topic
	  * @param id 需要更新的Topic的id
	  * @param dir 需要更新的Topic
	  */
	boolean updateTopic(Long id,Topic instance);
}

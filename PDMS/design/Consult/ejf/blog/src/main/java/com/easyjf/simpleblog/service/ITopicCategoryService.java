package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.TopicCategory;
/**
 * TopicCategoryService
 * @author EasyJWeb 1.0-m2
 * $Id: TopicCategoryService.java,v 0.0.1 2008-1-15 9:40:40 EasyJWeb 1.0-m2 Exp $
 */
public interface ITopicCategoryService {
	/**
	 * 保存一个TopicCategory，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addTopicCategory(TopicCategory instance);
	
	/**
	 * 根据一个ID得到TopicCategory
	 * 
	 * @param id
	 * @return
	 */
	TopicCategory getTopicCategory(Long id);
	
	/**
	 * 删除一个TopicCategory
	 * @param id
	 * @return
	 */
	boolean delTopicCategory(Long id);
	
	/**
	 * 批量删除TopicCategory
	 * @param ids
	 * @return
	 */
	boolean batchDelTopicCategorys(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到TopicCategory
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getTopicCategoryBy(IQueryObject queryObject);
	
	/**
	  * 更新一个TopicCategory
	  * @param id 需要更新的TopicCategory的id
	  * @param dir 需要更新的TopicCategory
	  */
	boolean updateTopicCategory(Long id,TopicCategory instance);
}

package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.Comment;
/**
 * CommentService
 * @author EasyJWeb 1.0-m2
 * $Id: CommentService.java,v 0.0.1 2008-1-15 9:40:18 EasyJWeb 1.0-m2 Exp $
 */
public interface ICommentService {
	/**
	 * 保存一个Comment，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addComment(Comment instance);
	
	/**
	 * 根据一个ID得到Comment
	 * 
	 * @param id
	 * @return
	 */
	Comment getComment(Long id);
	
	/**
	 * 删除一个Comment
	 * @param id
	 * @return
	 */
	boolean delComment(Long id);
	
	/**
	 * 批量删除Comment
	 * @param ids
	 * @return
	 */
	boolean batchDelComments(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Comment
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getCommentBy(IQueryObject queryObject);
		
	/**
	  * 更新一个Comment
	  * @param id 需要更新的Comment的id
	  * @param dir 需要更新的Comment
	  */
	boolean updateComment(Long id,Comment instance);
	
	/**
	 * 查询文章评论
	 * @param queryObject
	 * @return
	 */
	IPageList getTopicCommentBy(IQueryObject queryObject);
	
	/**
	 * 查询相册评论
	 * @param queryObject
	 * @return
	 */
	IPageList getAlbumCommentBy(IQueryObject queryObject);
}

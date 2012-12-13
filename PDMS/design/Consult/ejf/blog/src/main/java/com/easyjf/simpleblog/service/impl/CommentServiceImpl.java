package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.web.tools.IPageList;
import com.easyjf.simpleblog.domain.AlbumComment;
import com.easyjf.simpleblog.domain.Comment;
import com.easyjf.simpleblog.domain.TopicComment;
import com.easyjf.simpleblog.service.ICommentService;
import com.easyjf.simpleblog.dao.ICommentDAO;


/**
 * CommentServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: CommentServiceImpl.java,v 0.0.1 2008-1-15 9:40:18 EasyJWeb 1.0-m2 Exp $
 */
public class CommentServiceImpl implements ICommentService{
	
	private ICommentDAO commentDao;
	
	public void setCommentDao(ICommentDAO commentDao){
		this.commentDao=commentDao;
	}
	
	public Long addComment(Comment comment) {	
		this.commentDao.save(comment);		
		if (comment != null && comment.getId() != null) {
			return comment.getId();
		}
		return null;
	}
	
	public Comment getComment(Long id) {
		Comment comment = this.commentDao.get(id);
		return comment;
		}
	
	public boolean delComment(Long id) {	
			Comment comment = this.getComment(id);
			if (comment != null) {
				this.commentDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelComments(List<Serializable> commentIds) {
		
		for (Serializable id : commentIds) {
			delComment((Long) id);
		}
		return true;
	}
	
	public IPageList getCommentBy(IQueryObject queryObject) {
		return QueryUtil.query(queryObject, Comment.class,this.commentDao);		
	}
	
	public boolean updateComment(Long id, Comment comment) {
		if (id != null) {
			comment.setId(id);
		} else {
			return false;
		}
		this.commentDao.update(comment);
		return true;
	}

	public IPageList getAlbumCommentBy(IQueryObject queryObject) {
		return QueryUtil.query(queryObject, AlbumComment.class,this.commentDao);
	}

	public IPageList getTopicCommentBy(IQueryObject queryObject) {
		return QueryUtil.query(queryObject, TopicComment.class,this.commentDao);
	}	
}

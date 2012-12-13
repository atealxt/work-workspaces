package com.easyjf.simpleblog.dao;

import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.AlbumComment;
import com.easyjf.simpleblog.domain.TopicComment;
import com.easyjf.simpleblog.service.ICommentService;
import com.easyjf.web.tools.IPageList;

public class CommentServiceTest extends JpaDaoTest {
	private ICommentService service;

	public void setService(ICommentService service) {
		this.service = service;
	}

	public void testAddTopicComment() {
		TopicComment c=new TopicComment();
		c.setContent("文章评论");
		service.addComment(c);
		this.setComplete();
	}

	public void testGetTopicComment() {
		IPageList pageList=service.getTopicCommentBy(new QueryObject());
		System.out.println(pageList.getRowCount());
	}

	public void testAddAlbumComment() {
		AlbumComment c=new AlbumComment();
		c.setContent("相册评论");
		service.addComment(c);
		this.setComplete();
	}

	public void testGetAlbumComment() {
		IPageList pageList=service.getAlbumCommentBy(new QueryObject());
		System.out.println(pageList.getRowCount());
	}

}

package com.easyjf.simpleblog.service.impl;

import java.util.List;

import com.easyjf.simpleblog.dao.IBlogDAO;
import com.easyjf.simpleblog.domain.Blog;
import com.easyjf.simpleblog.service.IBlogService;

/**
 * BlogServiceImpl
 * 
 * @author EasyJWeb 1.0-m2 $Id: BlogServiceImpl.java,v 0.0.1 2008-1-17 17:01:36
 *         EasyJWeb 1.0-m2 Exp $
 */
public class BlogServiceImpl implements IBlogService {

	private IBlogDAO blogDao;

	public boolean adminLogin(String name, String password) {
		Blog blog = getBlog();
		if (name.equals(blog.getAuthor())
				&& password.equals(blog.getPassword()))
			return true;
		return false;
	}

	public Blog getBlog() {
		Blog blog = null;
		List list = blogDao.find(" 1=1 ", null, -1, -1);
		if (list == null || list.size() < 1) {
			blog = new Blog();
			blog.setTitle("简单Blog");
			blog.setAuthor("admin");
			blog.setPassword("admin");
			blog.setTheme("default");
			blogDao.save(blog);
		} else
			blog = (Blog) list.get(0);
		return blog;
	}

	public void setBlogDao(IBlogDAO blogDao) {
		this.blogDao = blogDao;
	}

	public boolean updateBlog(Blog blog) {
		blogDao.update(blog);
		return true;
	}
}

package com.easyjf.simpleblog.service;

import com.easyjf.simpleblog.domain.Blog;

/**
 * BlogService
 * 
 * @author EasyJWeb 1.0-m2 $Id: BlogService.java,v 0.0.1 2008-1-17 17:01:36
 *         EasyJWeb 1.0-m2 Exp $
 */
public interface IBlogService {
	boolean adminLogin(String name, String password);

	Blog getBlog();

	boolean updateBlog(Blog instance);
}

package com.easyjf.simpleblog.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.simpleblog.dao.IUserDAO;
import com.easyjf.simpleblog.domain.User;
import com.easyjf.simpleblog.service.IUserService;
import com.easyjf.simpleblog.service.LogicException;
import com.easyjf.web.tools.IPageList;

/**
 * UserServiceImpl
 * 
 * @author EasyJWeb 1.0-m2 $Id: UserServiceImpl.java,v 0.0.1 2008-1-15 9:40:47
 *         EasyJWeb 1.0-m2 Exp $
 */
public class UserServiceImpl implements IUserService {

	private IUserDAO userDao;

	public Long addUser(User user) {
		if (userDao.getBy("name", user.getName()) == null) {
			userDao.save(user);
			if (user != null && user.getId() != null)
				return user.getId();
		}
		return null;
	}

	public boolean batchDelUsers(List<Serializable> userIds) {

		for (Serializable id : userIds)
			delUser((Long) id);
		return true;
	}

	public boolean delUser(Long id) {
		User user = getUser(id);
		if(user.getComments().size()>0)throw new LogicException("用户已经发表了评论，不能直接删除！");
		if (user != null) {
			userDao.remove(id);
			return true;
		}
		return false;
	}

	public User getUser(Long id) {
		User user = userDao.get(id);
		return user;
	}

	public IPageList getUserBy(IQueryObject queryObject) {
		return QueryUtil.query(queryObject, User.class, userDao);
	}

	public User login(String userName, String password) {
		User user = userDao.getBy("name", userName);
		if (user != null)
			if (password.equals(user.getPassword()))
			{
				user.setLastLoginTime(new Date());
				user.setLoginTimes(user.getLoginTimes()+1);
				this.updateUser(user.getId(),user);
				return user;
			}
		return null;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean updateUser(Long id, User user) {
		if (id != null)
			user.setId(id);
		else
			return false;
		userDao.update(user);
		return true;
	}

}

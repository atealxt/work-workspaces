package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.User;
/**
 * UserService
 * @author EasyJWeb 1.0-m2
 * $Id: UserService.java,v 0.0.1 2008-1-15 9:40:47 EasyJWeb 1.0-m2 Exp $
 */
public interface IUserService {
	User login(String userName,String password);	
	/**
	 * 保存一个User，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addUser(User instance);
	
	/**
	 * 根据一个ID得到User
	 * 
	 * @param id
	 * @return
	 */
	User getUser(Long id);
	
	/**
	 * 删除一个User
	 * @param id
	 * @return
	 */
	boolean delUser(Long id);
	
	/**
	 * 批量删除User
	 * @param ids
	 * @return
	 */
	boolean batchDelUsers(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到User
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getUserBy(IQueryObject queryObject);
	
	/**
	  * 更新一个User
	  * @param id 需要更新的User的id
	  * @param dir 需要更新的User
	  */
	boolean updateUser(Long id,User instance);
}

package org.ywq.service;

import java.util.List;

import org.ywq.common.PageModel;
import org.ywq.entity.User;

/**
 * @author ai5qiangshao  E-mail:ai5qiangshao@163.com
 * @version 创建时间：Aug 7, 2009 8:09:06 PM
 * @Package org.ywq.service
 * @Description 类说明
 */
public interface IUserService {
	public User save(User us);
	public void delUser(Integer uid);
	
	public List<User> getUserList();
	
	public User login(String uname);
	
	public PageModel<User> list(Integer currPage,Integer pagesize,String where);

}
 
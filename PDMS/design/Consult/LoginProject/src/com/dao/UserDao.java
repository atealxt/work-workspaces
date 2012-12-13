package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.User;

public class UserDao extends HibernateDaoSupport{
	
	//检索数据库，如果找到匹配记录，则返回true，否则返回false
	//解下来，该为项目导入hibernate能力，因为我们是在用Spring集成的HibernateTemplate
	//由于要在dao当中操作数据对象User,所以，我们还需要编写对应User的映射文件
	//接下来，我们需要将已经开发好的UserDao交给Spring容器来管理，也就是说我们要配置spring配置文件了
	public boolean loginCheck(User loginUser){
		List results = super.getHibernateTemplate().find("from User where userName=? and password=?", 
				new String[]{loginUser.getUserName(),loginUser.getPassword()});
		return results!=null && !results.isEmpty();
	}
}

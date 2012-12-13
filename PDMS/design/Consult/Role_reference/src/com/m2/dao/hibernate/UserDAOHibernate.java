package com.m2.dao.hibernate;

import java.util.List;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.m2.dao.UserDAO;
import com.m2.entity.User;


public class UserDAOHibernate  extends BaseDAOHibernate implements UserDAO{
	
	public void addUser(User user) {
		save(user);
	}
	
	public void updateUser(User user) {
		update(user);
	}
	
	public void delUser(User user) {
		delete(user);
	}
	
	public void delUserById(final int userid){
		
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException, SQLException {
				Query query = s.createQuery(DEL_USER_BY_ID);
				query.setInteger(0, userid);
				query.executeUpdate();
				return null;
			}
		});
	}
	
	public User findUserById(int id){
		return (User)findById("User",id);
	}
	
	
	public User findUserByLoginName(String loginName){
		List list = this.getHibernateTemplate().find(FIND_USER_BY_LOGIN_NAME, loginName);
		if (list.size()>0) 
			return (User)list.get(0);
		return null;
	}	

}

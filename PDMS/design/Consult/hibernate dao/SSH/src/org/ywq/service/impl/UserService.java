package org.ywq.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.ywq.common.BaseDAOHibernate;
import org.ywq.common.HibernateQueryBuilder;
import org.ywq.common.IDAO;
import org.ywq.common.PageModel;
import org.ywq.common.QueryBuilder;
import org.ywq.entity.User;
import org.ywq.service.IUserService;

public class UserService implements IUserService {

	private IDAO<User, Serializable> dao;

	public void setDao(IDAO<User, Serializable> dao) {
		this.dao = dao;
	}

	public User saveUser(User us) {
		return (User) dao.save(us);
	}

	@SuppressWarnings("unchecked")
	public PageModel<User> getpage(Integer start, Integer end,
			QueryBuilder<User> qb) {
		qb.addCriterion(Restrictions.like("uname", "%qian%"));
		return dao.find(start, end, qb);
	}

	public User login(String uname) {
		List<User> list = dao.find(new HibernateQueryBuilder<User>(User.class)
				.eq("uname", uname));
		if (list.size() > 0) {
			System.out.println(dao.count(new HibernateQueryBuilder<User>(
					User.class)));
			return list.get(0);

		}
		return null;

	}

	public PageModel<User> list(Integer currPage, Integer pageSize,String where) {
		QueryBuilder<User> qb = new HibernateQueryBuilder<User>(User.class)
				.addOrderBy(Order.asc("uid"));
		qb.like("uname", "%"+where+"%");
		return dao.find(currPage, pageSize, qb);
	}

	public void delUser(Integer uid) {

	}

	public List<User> getUserList() {
		return null;
	}

	public User save(User us) {
		return (User) dao.save(us);
	}

}

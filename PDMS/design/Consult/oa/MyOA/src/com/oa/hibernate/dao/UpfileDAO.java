package com.oa.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.hibernate.beans.Upfile;
import com.oa.struts.util.Constants;
import com.oa.struts.util.Pager;

public class UpfileDAO extends HibernateDaoSupport implements IUpfileDAO {
	
	public Upfile getFile(String id) {
		return (Upfile) getHibernateTemplate().get(Upfile.class,new Integer(id));
	}

	public List getFiles() {
		return getHibernateTemplate().find("from Upfile");
	}
	
	public Pager findPagerAllFile(){
		return findPagerAllFile(Constants.pageSize, Constants.pageNo);
	}
	
	public Pager findPagerAllFile(final int pageSize,final int pageNo){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		// set query condition
		Criteria criteria = session.createCriteria(Upfile.class);
		// get total count
		int rowCount = ((Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		
		// get current page list
		int startIndex = pageSize * (pageNo - 1);
		criteria.addOrder(Order.desc("fileuptime"));
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		
		session.close();

		return new Pager(pageSize, pageNo, rowCount, result);
		
	}

	public Upfile findById(String id){	
		return (Upfile) getHibernateTemplate().get(Upfile.class,
				new Integer(id));
		
	}
	
	public Upfile findByUptime(final String fileuptime)
	{
		return (Upfile) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Upfile result = (Upfile)session.createCriteria(Upfile.class).add(
						Restrictions.eq("fileuptime", fileuptime)).uniqueResult();
				return result;
			}
		});
	}

	public void upload(Upfile upfile){
		getHibernateTemplate().save(upfile);
		
	}

	public void view(Upfile upfile){
		getHibernateTemplate().update(upfile);

	}

	public void delete(String id){
		Object p = getHibernateTemplate().load(Upfile.class, new Integer(id));
		getHibernateTemplate().delete(p);
	}

}

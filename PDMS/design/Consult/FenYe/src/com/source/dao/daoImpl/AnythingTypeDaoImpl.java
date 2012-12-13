/**
 * 
 */
package com.source.dao.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.papa.list.Searcher;
import com.papa.util.DateUtil;
import com.source.bean.AnythingType;
import com.source.dao.AnythingTypeDao;

/**
 * @author ¡÷‘æª‘ [Oct 10, 2007]
 */
public class AnythingTypeDaoImpl extends HibernateDaoSupport implements
		AnythingTypeDao
{
	private static final Log log=LogFactory.getLog(AnythingTypeDao.class);

	public void save(AnythingType type)
	{
		log.debug("saving AnythingType instance");
		try
		{
			if (type.getClassId()==0)
				type.setClassId(null);
			getHibernateTemplate().saveOrUpdate(type);
			log.debug("save successful");
		}
		catch (RuntimeException re)
		{
			log.error("save failed",re);
			throw re;
		}
	}

	public void delete(AnythingType persistentInstance)
	{
		log.debug("deleting AnythingType instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		}
		catch (RuntimeException re)
		{
			log.error("delete failed",re);
			throw re;
		}
	}

	public AnythingType findById(java.lang.Integer id)
	{
		log.debug("getting AnythingType instance with id: "+id);
		try
		{
			AnythingType instance=(AnythingType)getHibernateTemplate().get(
					"com.source.bean.AnythingType",id);
			return instance;
		}
		catch (RuntimeException re)
		{
			log.error("get failed",re);
			throw re;
		}
	}

	public List findByExample(AnythingType instance)
	{
		log.debug("finding AnythingType instance by example");
		try
		{
			List results=getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+results.size());
			return results;
		}
		catch (RuntimeException re)
		{
			log.error("find by example failed",re);
			throw re;
		}
	}

	public List findByProperty(String propertyName,Object value)
	{
		log.debug("finding AnythingType instance with property: "+propertyName
				+", value: "+value);
		try
		{
			String queryString="from AnythingType as model where model."
					+propertyName+"= ?";
			return getHibernateTemplate().find(queryString,value);
		}
		catch (RuntimeException re)
		{
			log.error("find by property name failed",re);
			throw re;
		}
	}

	public List search(final int first,final int pageno,final Searcher searcher)
	{
		List typeList=(ArrayList)getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
							throws HibernateException,SQLException
					{
						Criteria criteria=session
								.createCriteria(AnythingType.class);
						criteria=criteria.setFirstResult(first).setMaxResults(
								pageno);
						criteria=criteria.addOrder(Order.desc("classId"));
						String propertyName="";
						byte searchType=searcher.getSearchType();
						switch (searchType)
						{
						case 1:
							propertyName="className";
							break;
						case 2:
							propertyName="classMark";
							break;
						}
						String searchValue="%"+searcher.getSearchValue()+"%";
						if (!propertyName.equals(""))
							criteria.add(Expression.ilike(propertyName,
									searchValue));
						String beginDate=searcher.getBeginDate();
						String endDate=searcher.getEndDate();
						if (!beginDate.equals(""))
							criteria.add(Expression.ge("createDate",DateUtil
									.parseDate(beginDate).getTime()));
						if (!endDate.equals(""))
						{
							Calendar calendar=DateUtil.parseDate(endDate);
							calendar.add(Calendar.DATE,1);
							Date endDateAdd1=calendar.getTime();
							criteria.add(Expression
									.le("createDate",endDateAdd1));
						}
						List list=criteria.list();
						return list;
					}
				});
		return typeList;
	}

	public int count(final Searcher searcher)
	{
		int count=(Integer)getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
							throws HibernateException,SQLException
					{
						Criteria criteria=session
								.createCriteria(AnythingType.class);
						String propertyName="";
						byte searchType=searcher.getSearchType();
						switch (searchType)
						{
						case 1:
							propertyName="className";
							break;
						case 2:
							propertyName="classMark";
							break;
						}
						String searchValue="%"+searcher.getSearchValue()+"%";
						if (!propertyName.equals(""))
							criteria.add(Expression.ilike(propertyName,
									searchValue));
						String beginDate=searcher.getBeginDate();
						String endDate=searcher.getEndDate();
						if (!beginDate.equals(""))
							criteria.add(Expression.ge("createDate",DateUtil
									.parseDate(beginDate).getTime()));
						if (!endDate.equals(""))
						{
							Calendar calendar=DateUtil.parseDate(endDate);
							calendar.add(Calendar.DATE,1);
							Date endDateAdd1=calendar.getTime();
							criteria.add(Expression
									.le("createDate",endDateAdd1));
						}
						return (Integer)criteria.setProjection(
								Projections.rowCount()).uniqueResult();
					}
				});
		return count;
	}
}

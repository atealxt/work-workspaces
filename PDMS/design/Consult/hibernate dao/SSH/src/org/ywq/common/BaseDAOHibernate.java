package org.ywq.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.ywq.common.utils.ObjectWriteorRead;

/**
 * 基本实现类
 * 
 * @spring.property name="sessionFactory" ref="sessionFactory" type="bean"
 *                  注入sessionFactory
 */
public class BaseDAOHibernate<T> extends HibernateDaoSupport implements
		IDAO<T, Serializable> {

	@SuppressWarnings("unchecked")
	public PageModel<T> find(final Integer currentPage, final Integer pageSize,
			final QueryBuilder qb) {
		QueryBuilder newqb = null;
		if (qb != null) {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectWriteorRead.writeObject(qb, byteOut);// 序列化对象
			ByteArrayInputStream bytein = new ByteArrayInputStream(byteOut
					.toByteArray());
			newqb = (QueryBuilder) ObjectWriteorRead.readObject(bytein);// 还原对象，以实现拷贝对象
			Integer total = this.count(newqb);
			PageModel pm = new PageModel();
			pm.setDatas(this.find(qb, currentPage, pageSize));
			pm.setTotal(total);
			pm.setPagesize(pageSize);
			pm.setTotalPage((total + pageSize - 1) / pageSize);
			pm.setCurrenPage((currentPage));
			return pm;
		}
		return null;
	}

	protected final Log log = LogFactory.getLog(getClass());

	public BaseDAOHibernate() {
		System.out.println("creating BaseDAOHibernate... ");
	}

	@SuppressWarnings("unchecked")
	public List<T> getObjects(Class<T> clazz) {
		Assert.notNull(clazz, "要查询的对象类型不能为空!");
		return getHibernateTemplate().loadAll(clazz);
	}

	public Object getObject(Class<T> clazz, Serializable id) {
		Assert.notNull(clazz, "要查询的对象类型不能为空!");
		Assert.notNull(id, "要查询的对象主键不能为空!");
		return getHibernateTemplate().get(clazz, id);
	}

	public Object save(Object o) {
		Assert.notNull(o, "要保存的对象不能为空!");
		getHibernateTemplate().save(o);
		return o;
	}

	public Object saveOrUpdate(Object o) {
		Assert.notNull(o, "要保存或更新的对象类型不能为空!");
		getHibernateTemplate().saveOrUpdate(o);
		return o;
	}

	public Object update(Object object) {
		Assert.notNull(object, "要更新的对象类型不能为空!");
		getHibernateTemplate().update(object);
		return object;
	}

	@SuppressWarnings("unchecked")
	public void removeObject(Class<T> clazz, Serializable id) {
		Assert.notNull(clazz, "要移除的对象类型不能为空!");
		Assert.notNull(id, "要移除的对象主键不能为空!");
		Object object = getObject(clazz, id);
		if (object != null) {
			getHibernateTemplate().delete(object);
		}
	}

	public void removeObject(Object object) {
		Assert.notNull(object, "要移除的对象不能为空!");
		getHibernateTemplate().delete(object);
	}

	public void removeBatch(final Class<T> clazz, final Serializable[] ids) {
		Assert.notNull(clazz, "要移除的对象类型不能为空!");
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for (int i = 0; i < ids.length; i++) {
					Object obj = session.load(clazz, ids[i]);
					if (obj != null) {
						session.delete(obj);
					} else {
						log.warn("无法删除主键为:" + ids[i] + "的" + clazz.getName());
					}
				}
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String query) {
		Assert.notNull(query, "查询语句不能为空!");
		return getHibernateTemplate().find(query);
	}

	public void refresh(Object object) {
		getHibernateTemplate().refresh(object);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public List<T> find(String query, Integer start, Integer length) {
		return getObjects(query, start, length);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(final QueryBuilder queryBuilder,
			final Integer currentPage, final Integer pageSize) {
		if (queryBuilder != null) {
			return (List<T>) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							DetachedCriteria dc = queryBuilder
									.getDetachedCriteria();
							for (Object or : queryBuilder.getOrderBys()) {
								Order order = (Order) or;
								dc.addOrder(order);
							}
							if (currentPage != null && pageSize != null) {
								Criteria crit = dc
										.getExecutableCriteria(session);
								crit.setFirstResult((currentPage - 1)
										* pageSize);
								crit.setMaxResults(pageSize);
							}
							return dc.getExecutableCriteria(session).list();
						}
					}, true);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> getObjects(final String queryString, final int position,
			final int length) {
		Assert.notNull(queryString, "查询语句不能为空!");
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);
				query.setFirstResult(position);
				query.setMaxResults(length);
				List lt = query.list();

				return lt;
			}
		});
	}

	/*
	 * （非 Javadoc）
	 * 
	 */
	public List<T> find(final QueryBuilder<T> queryBuilder) {
		if (queryBuilder != null) {
			return this.find(queryBuilder, null, null);
		}
		return null;
	}

	/**
	 * @param hql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final String hql, final Serializable param) {
		Assert.notNull(hql, "查询语句不能为空!");
		Assert.notNull(param, "查询参数不能为空!");

		return getHibernateTemplate().find(hql, param);
	}

	/**
	 * @param clazz
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(final Class<T> clazz, final Criterion[] criterions) {
		Assert.notNull(clazz, "要查询的类不能为空!");
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria query = session.createCriteria(clazz);
				if (criterions != null && criterions.length > 0) {
					for (int i = 0; i < criterions.length; i++) {
						query.add(criterions[i]);
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 得到查到的最前一个对象.
	 * 
	 * @param clazz
	 *            类名.
	 * @param criterions
	 *            查询条件.
	 * @return 查询得到的结果.
	 */
	public Object getFirst(Class<T> clazz, Criterion[] criterions) {
		List<T> lt = getList(clazz, criterions);
		if (lt != null && !lt.isEmpty()) {
			return lt.get(0);
		}
		return null;
	}

	/**
	 * @param clazz
	 * @param criterions
	 * @param orders
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(final Class<T> clazz, final Criterion[] criterions,
			final Order[] orders) {
		Assert.notNull(clazz, "要查询的类不能为空!");
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				if (criterions != null && criterions.length > 0) {
					for (int i = 0; i < criterions.length; i++) {
						criteria.add(criterions[i]);
					}
				}
				if (orders != null && orders.length > 0) {
					for (int i = 0; i < orders.length; i++) {
						criteria.addOrder(orders[i]);
					}
				}
				return criteria.list();
			}
		});
	}

	/**
	 * @param className
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(final String className, final Criterion[] criterions) {
		Assert.notNull(className, "要查询的类名不能为空");
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = null;
				try {
					criteria = session.createCriteria(Class.forName(className));
				} catch (ClassNotFoundException e) {
					logger
							.error(
									"$HibernateCallback.doInHibernate(Session) - 找不到类错误 - e=" + e, e); //$NON-NLS-1$
					throw new IllegalArgumentException("传入的类名是错误的,classname = "
							+ className);
				}
				if (criterions != null && criterions.length > 0) {
					for (int i = 0; i < criterions.length; i++) {
						criteria.add(criterions[i]);
					}
				}
				return criteria.list();
			}
		});
	}

	/**
	 * @param className
	 * @param sequenceName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getNextId(final String sequenceName) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = null;
				query = session.createSQLQuery("select " + sequenceName
						+ ".nextval from dual");
				return query.list();
			}
		});
	}

	public int count(final String hql) {
		return ((Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.setProperties(Projections.rowCount())
								.uniqueResult();
					}
				}, true)).intValue();
	}

	/**
	 * @param qb
	 * @return
	 */
	public int count(final QueryBuilder<T> queryBuilder) {
		return ((Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						System.out.println(session.isOpen());
						DetachedCriteria dc = queryBuilder
								.getDetachedCriteria();
						Criteria ct = dc.getExecutableCriteria(session);
						return ct.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				}, true)).intValue();
	}

	public int updateBySql(final String sql) {
		super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int iRet = session.createSQLQuery(sql).executeUpdate();
				return iRet;
			}
		});
		return -1;
	}

	public void updateBatch(final List<T> objs, final int batchSize) {
		super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 0;
				for (Object o : objs) {
					session.update(o);
					if (++count % batchSize == 0) {
						session.flush();
						session.clear();
					}
				}
				return true;
			}
		});

	}

}

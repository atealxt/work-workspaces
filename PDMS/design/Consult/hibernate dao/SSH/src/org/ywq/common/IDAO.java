package org.ywq.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface IDAO<T,ID extends Serializable> {

	/**
	 * 查询类clazz的所有对象.
	 * 
	 * @param clazz
	 *            the type of objects (a.k.a. while table) to get data from
	 * @return List of populated objects
	 */
	public List<T> getObjects(Class<T> clazz);

	/**
	 * 根据类名和主键得到一个对象.
	 * 
	 * @param clazz
	 *            model class to lookup
	 * @param id
	 *            the identifier (primary key) of the class
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	public Object getObject(Class<T> clazz, Serializable id);

	/**
	 * 插入一个对象.
	 * 
	 * @param o
	 *            the object to save
	 */
	public Object save(Object o);

	/**
	 * 持久化一个对象,包括更新或插入.
	 * 
	 * @param o
	 *            the object to save
	 * 
	 */
	public Object saveOrUpdate(Object o);

	/**
	 * 更新一个对象.
	 * 
	 * @param object
	 */
	public Object update(Object object);

	/**
	 * 根据类和主键,删除一个对象.
	 * 
	 * @param clazz
	 *            model class to lookup
	 * @param id
	 *            the identifier (primary key) of the class
	 */
	public void removeObject(Class<T> clazz, Serializable id);

	/**
	 * 删除一个对象.
	 */
	public void removeObject(Object object);

	/**
	 * 批量删除对象.
	 * 
	 * @param clazz
	 * @param ids
	 */
	public void removeBatch(Class<T> clazz, Serializable[] ids);

	/**
	 * 根据HQL查询设置.
	 * 
	 * @param filter
	 * @return
	 */
	public List<T> find(String query);

	/**
	 * 根据查询设置得到查询结果
	 * 
	 * @param query
	 * @return
	 */
	public List<T> find(QueryBuilder<T> queryBuilder);

	/**
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            页大小
	 * @param queryBuilder
	 *            查询类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageModel find(Integer currentPage, Integer pageSize, QueryBuilder queryBuilder);

	/**
	 * 更新对象.
	 * 
	 * @param object
	 *            需要更新的对象.
	 */
	public void refresh(Object object);

	/**
	 * 更新到数据库中.
	 */
	public void flush();

	/**
	 * 根据查询设置,得到分页的数据结果集.
	 * 
	 * @param query
	 * @param start
	 *        offset
	 * @param length
	 *     size
	 * @return
	 */
	public List<T> find(String query, Integer start, Integer length);

	/**
	 * 
	 * @param queryBulider
	 *        查询条件
	 * @param currentPage
	 *        当前页
	 * @param pageSize
	 *  
	 * @return
	 */
	public List<T> find(QueryBuilder<T> queryBulider, Integer currentPage, Integer pageSize);

	/**
	 * 根据条件查询.
	 * 
	 * @param hql
	 *            hql语句.
	 * @param param
	 *            参数.
	 * @return 查询结果.
	 */
	public List<T> find(String hql, Serializable param);

	/**
	 * 根据条件查询相关结果.
	 * 
	 * @param clazz
	 *            要查询的对象.
	 * @param criterions
	 *            查询条件.
	 * @return 查询结果.
	 */
	public List<T> getList(Class<T> clazz, Criterion[] criterions);

	/**
	 * @param clazz
	 * @param criterions
	 * @return
	 */
	public Object getFirst(Class<T> clazz, Criterion[] criterions);

	/**
	 * @param class1
	 * @param 条件查询数组
	 * @param 排序
	 * @return
	 */
	public List<T> getList(Class<T> class1, Criterion[] criterions,
			Order[] orders);

	/**
	 * @param className
	 * @param criterions
	 * @return
	 */
	public List<T> getList(String className, Criterion[] criterions);

	/**
	 * @param className
	 * @param sequenceName
	 * @return
	 */
	public List<T> getNextId(String sequenceName);

	/**
	 * @param qb
	 * @return
	 */
	public int count(QueryBuilder<T> qb);

	/*
	 * 
	 */
	public int count(String hql);

	/**
	 * 执行SQL语句， 返回影响结果的行数
	 * 
	 * @param sql
	 * @return
	 */
	public int updateBySql(String sql);

	/**
	 * 批量修改对象
	 * 
	 * @param objs
	 * @param batchSize
	 */
	public void updateBatch(List<T> objs, int batchSize);

}
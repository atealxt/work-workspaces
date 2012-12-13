package org.ywq.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface IDAO<T,ID extends Serializable> {

	/**
	 * ��ѯ��clazz�����ж���.
	 * 
	 * @param clazz
	 *            the type of objects (a.k.a. while table) to get data from
	 * @return List of populated objects
	 */
	public List<T> getObjects(Class<T> clazz);

	/**
	 * ���������������õ�һ������.
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
	 * ����һ������.
	 * 
	 * @param o
	 *            the object to save
	 */
	public Object save(Object o);

	/**
	 * �־û�һ������,�������»����.
	 * 
	 * @param o
	 *            the object to save
	 * 
	 */
	public Object saveOrUpdate(Object o);

	/**
	 * ����һ������.
	 * 
	 * @param object
	 */
	public Object update(Object object);

	/**
	 * �����������,ɾ��һ������.
	 * 
	 * @param clazz
	 *            model class to lookup
	 * @param id
	 *            the identifier (primary key) of the class
	 */
	public void removeObject(Class<T> clazz, Serializable id);

	/**
	 * ɾ��һ������.
	 */
	public void removeObject(Object object);

	/**
	 * ����ɾ������.
	 * 
	 * @param clazz
	 * @param ids
	 */
	public void removeBatch(Class<T> clazz, Serializable[] ids);

	/**
	 * ����HQL��ѯ����.
	 * 
	 * @param filter
	 * @return
	 */
	public List<T> find(String query);

	/**
	 * ���ݲ�ѯ���õõ���ѯ���
	 * 
	 * @param query
	 * @return
	 */
	public List<T> find(QueryBuilder<T> queryBuilder);

	/**
	 * 
	 * @param currentPage
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ҳ��С
	 * @param queryBuilder
	 *            ��ѯ��
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageModel find(Integer currentPage, Integer pageSize, QueryBuilder queryBuilder);

	/**
	 * ���¶���.
	 * 
	 * @param object
	 *            ��Ҫ���µĶ���.
	 */
	public void refresh(Object object);

	/**
	 * ���µ����ݿ���.
	 */
	public void flush();

	/**
	 * ���ݲ�ѯ����,�õ���ҳ�����ݽ����.
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
	 *        ��ѯ����
	 * @param currentPage
	 *        ��ǰҳ
	 * @param pageSize
	 *  
	 * @return
	 */
	public List<T> find(QueryBuilder<T> queryBulider, Integer currentPage, Integer pageSize);

	/**
	 * ����������ѯ.
	 * 
	 * @param hql
	 *            hql���.
	 * @param param
	 *            ����.
	 * @return ��ѯ���.
	 */
	public List<T> find(String hql, Serializable param);

	/**
	 * ����������ѯ��ؽ��.
	 * 
	 * @param clazz
	 *            Ҫ��ѯ�Ķ���.
	 * @param criterions
	 *            ��ѯ����.
	 * @return ��ѯ���.
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
	 * @param ������ѯ����
	 * @param ����
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
	 * ִ��SQL��䣬 ����Ӱ����������
	 * 
	 * @param sql
	 * @return
	 */
	public int updateBySql(String sql);

	/**
	 * �����޸Ķ���
	 * 
	 * @param objs
	 * @param batchSize
	 */
	public void updateBatch(List<T> objs, int batchSize);

}
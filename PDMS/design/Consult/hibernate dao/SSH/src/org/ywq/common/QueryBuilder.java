package org.ywq.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

/**
 * ��ѯ������.
 * 
 */
public interface QueryBuilder<T> extends Serializable {
	
	public void setDc(DetachedCriteria ec);

	public abstract QueryBuilder<T> clone();

	/**
	 * �ж��Ƿ����
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> eq(String propertyName, Object value);

	/**
	 * �ж��Ƿ�ƥ��
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> like(String propertyName, Object value);

	/**
	 * ����matchMode�ж��Ƿ�ƥ��
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 */
	public abstract QueryBuilder<T> like(String propertyName, String value,
			MatchMode matchMode);

	/**
	 * ����matchMode�ж��Ƿ�ƥ��
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 */
	public abstract QueryBuilder<T> ilike(String propertyName, String value,
			MatchMode matchMode);

	/**
	 * �ж�ʱ�򲻵�
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> ilike(String propertyName, Object value);

	/**
	 * �ж��Ƿ����
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> gt(String propertyName, Object value);

	/**
	 * �ж��Ƿ�С��
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> lt(String propertyName, Object value);

	/**
	 * �ж��Ƿ�С�ڵ���
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> le(String propertyName, Object value);

	/**
	 * �ж��Ƿ���ڵ���
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> ge(String propertyName, Object value);

	/**
	 * ������.
	 * @param propertyName
	 * @param value
	 */
	public QueryBuilder<T> notEq(String propertyName, Object value);

	/**
	 * �ж��Ƿ��ڸ�����������
	 * @param propertyName
	 * @param lo
	 * @param hi
	 */
	public abstract QueryBuilder<T> between(String propertyName, Object lo,
			Object hi);

	/**
	 * �ж��Ƿ��ڸ�����������
	 * @param propertyName
	 * @param values
	 */
	public abstract QueryBuilder<T> in(String propertyName, Object[] values);

	/**
	 * �ж��Ƿ��ڸ����ļ�����
	 * @param propertyName
	 * @param values
	 */
	public abstract QueryBuilder<T> in(String propertyName, Collection<T> values);

	/**
	 * �ж��Ƿ�Ϊ��
	 * @param propertyName
	 */
	public abstract QueryBuilder<T> isNull(String propertyName);

	/**
	 * �ж�����ֵ�Ƿ����
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> eqProperty(String propertyName,
			String otherPropertyName);

	/**
	 * �ж�����ֵ�Ƿ�С��
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> ltProperty(String propertyName,
			String otherPropertyName);

	/**
	 * �ж�����ֵ�Ƿ�С�ڵ���
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> leProperty(String propertyName,
			String otherPropertyName);

	/**
	 * �ж��Ƿ񲻵���
	 * @param propertyName
	 */
	public abstract QueryBuilder<T> isNotNull(String propertyName);

	/**
	 * @param propertyNameValues
	 * @return
	 */
	public abstract QueryBuilder<T> allEq(Map<T, T> propertyNameValues);

	/** 
	 * �������
	 * @param orderBy
	 * @return
	 */
	public abstract QueryBuilder<T> addOrderBy(Order orderBy);

	/**
	 * ��ѯ����
	 * ������ʹ��
	 * @param criterion
	 * @return
	 */
	public abstract QueryBuilder<T> addCriterion(Criterion criterion);

	/**
	 * 
	 * @return
	 */
	public abstract List<Order> getOrderBys();

	/**
	 * 
	 * @return
	 */
	public abstract DetachedCriteria getDetachedCriteria();

	public abstract Class<T> getClazz();

}
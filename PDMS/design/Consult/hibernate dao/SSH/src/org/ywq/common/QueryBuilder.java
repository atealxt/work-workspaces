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
 * 查询过滤类.
 * 
 */
public interface QueryBuilder<T> extends Serializable {
	
	public void setDc(DetachedCriteria ec);

	public abstract QueryBuilder<T> clone();

	/**
	 * 判断是否相等
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> eq(String propertyName, Object value);

	/**
	 * 判断是否匹配
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> like(String propertyName, Object value);

	/**
	 * 根据matchMode判断是否匹配
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 */
	public abstract QueryBuilder<T> like(String propertyName, String value,
			MatchMode matchMode);

	/**
	 * 根据matchMode判断是否匹配
	 * @param propertyName
	 * @param value
	 * @param matchMode
	 */
	public abstract QueryBuilder<T> ilike(String propertyName, String value,
			MatchMode matchMode);

	/**
	 * 判断时候不等
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> ilike(String propertyName, Object value);

	/**
	 * 判断是否大于
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> gt(String propertyName, Object value);

	/**
	 * 判断是否小于
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> lt(String propertyName, Object value);

	/**
	 * 判断是否小于等于
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> le(String propertyName, Object value);

	/**
	 * 判断是否大于等于
	 * @param propertyName
	 * @param value
	 */
	public abstract QueryBuilder<T> ge(String propertyName, Object value);

	/**
	 * 不等于.
	 * @param propertyName
	 * @param value
	 */
	public QueryBuilder<T> notEq(String propertyName, Object value);

	/**
	 * 判断是否在给定的区间里
	 * @param propertyName
	 * @param lo
	 * @param hi
	 */
	public abstract QueryBuilder<T> between(String propertyName, Object lo,
			Object hi);

	/**
	 * 判断是否在给定的数组里
	 * @param propertyName
	 * @param values
	 */
	public abstract QueryBuilder<T> in(String propertyName, Object[] values);

	/**
	 * 判断是否在给定的集合里
	 * @param propertyName
	 * @param values
	 */
	public abstract QueryBuilder<T> in(String propertyName, Collection<T> values);

	/**
	 * 判断是否为空
	 * @param propertyName
	 */
	public abstract QueryBuilder<T> isNull(String propertyName);

	/**
	 * 判断属性值是否相等
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> eqProperty(String propertyName,
			String otherPropertyName);

	/**
	 * 判断属性值是否小于
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> ltProperty(String propertyName,
			String otherPropertyName);

	/**
	 * 判断属性值是否小于等于
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public abstract QueryBuilder<T> leProperty(String propertyName,
			String otherPropertyName);

	/**
	 * 判断是否不等于
	 * @param propertyName
	 */
	public abstract QueryBuilder<T> isNotNull(String propertyName);

	/**
	 * @param propertyNameValues
	 * @return
	 */
	public abstract QueryBuilder<T> allEq(Map<T, T> propertyNameValues);

	/** 
	 * 添加排序
	 * @param orderBy
	 * @return
	 */
	public abstract QueryBuilder<T> addOrderBy(Order orderBy);

	/**
	 * 查询条件
	 * 不建议使用
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
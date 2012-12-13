package org.ywq.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 使用hibernate进行查询的实现.
 * 
 */
public class HibernateQueryBuilder<T> implements QueryBuilder<T>, Serializable,
		Cloneable {

	@SuppressWarnings("unchecked")
	public HibernateQueryBuilder<T> clone() {
		HibernateQueryBuilder<T> qb = null;
		try {
			qb = (HibernateQueryBuilder<T>) super.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return qb;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7000654904767633672L;

	private DetachedCriteria dc = null;

	private List<Order> orderLists = new ArrayList<Order>();

	private Class<T> clazz = null;

	public HibernateQueryBuilder(Class<T> clazz) {
		this.clazz = clazz;
		dc = DetachedCriteria.forClass(clazz);
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}

	public QueryBuilder<T> eq(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.eq(propertyName, value));
		}
		return this;
	}

	/**
	 * 不等于.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public QueryBuilder<T> notEq(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.ne(propertyName, value));
		}
		return this;
	}

	private boolean isNotEmpty(Object value) {
		return value != null && value.toString().trim().length() > 0;
	}

	public QueryBuilder<T> like(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.like(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> like(String propertyName, String value,
			MatchMode matchMode) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.like(propertyName, value, matchMode));
		}
		return this;
	}

	public QueryBuilder<T> ilike(String propertyName, String value,
			MatchMode matchMode) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.ilike(propertyName, value, matchMode));

		}
		return this;
	}

	public QueryBuilder<T> ilike(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.ilike(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> gt(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.gt(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> lt(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.lt(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> le(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.le(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> ge(String propertyName, Object value) {
		if (isNotEmpty(value)) {
			dc.add(Restrictions.ge(propertyName, value));
		}
		return this;
	}

	public QueryBuilder<T> between(String propertyName, Object lo, Object hi) {
		if (isNotEmpty(lo) && isNotEmpty(hi)) {
			dc.add(Restrictions.between(propertyName, lo, hi));
		} else if (isNotEmpty(lo)) {
			dc.add(Restrictions.ge(propertyName, lo));
		} else if (isNotEmpty(hi)) {
			dc.add(Restrictions.le(propertyName, hi));
		}
		return this;
	}

	public QueryBuilder<T> in(String propertyName, Object[] values) {
		if (values != null && values.length > 0) {
			dc.add(Restrictions.in(propertyName, values));
		}
		return this;
	}

	public QueryBuilder<T> in(String propertyName, Collection<T> values) {
		if (values != null && values.size() > 0) {
			dc.add(Restrictions.in(propertyName, values));
		}
		return this;
	}

	public QueryBuilder<T> isNull(String propertyName) {
		dc.add(Restrictions.isNull(propertyName));
		return this;
	}

	public QueryBuilder<T> eqProperty(String propertyName,
			String otherPropertyName) {
		dc.add(Restrictions.eqProperty(propertyName, otherPropertyName));
		return this;
	}

	public QueryBuilder<T> ltProperty(String propertyName,
			String otherPropertyName) {
		dc.add(Restrictions.ltProperty(propertyName, otherPropertyName));
		return this;
	}

	public QueryBuilder<T> leProperty(String propertyName,
			String otherPropertyName) {
		dc.add(Restrictions.leProperty(propertyName, otherPropertyName));
		return this;
	}

	public QueryBuilder<T> isNotNull(String propertyName) {
		dc.add(Restrictions.isNotNull(propertyName));
		return this;

	}

	public QueryBuilder<T> allEq(Map<T, T> propertyNameValues) {
		dc.add(Restrictions.allEq(propertyNameValues));
		return this;
	}

	public QueryBuilder<T> addOrderBy(Order orderBy) {
		orderLists.add(orderBy);
		return this;
	}

	public DetachedCriteria getDetachedCriteria() {
		return dc;
	}

	public List<Order> getOrderBys() {
		return orderLists;
	}

	public QueryBuilder<T> addCriterion(Criterion criterion) {
		if (criterion != null) {
			dc.add(criterion);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		if (!(object instanceof HibernateQueryBuilder)) {
			return false;
		}
		HibernateQueryBuilder<T> rhs = (HibernateQueryBuilder<T>) object;
		return new EqualsBuilder().append(this.clazz, rhs.clazz).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-2022315247, 1437659757).append(this.clazz)
				.toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("orderLists", this.orderLists).append("clazz",
						this.clazz).toString();
	}

}

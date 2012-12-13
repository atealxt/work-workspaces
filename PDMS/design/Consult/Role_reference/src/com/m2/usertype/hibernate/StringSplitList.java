package com.m2.usertype.hibernate;

import org.hibernate.usertype.UserType;
import org.hibernate.HibernateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;


/**
 * <p>
 * @author: http://yuetong.javaeye.com
 * </p>
 * 注意，分隔符隔开的类型是String
 * 
 *
 */
public class StringSplitList implements UserType {

	private static final String SPLITTER = ",";

	private static final int[] TYPES = new int[] { Types.VARCHAR };

	public StringSplitList() {
	}


	public int[] sqlTypes() {
		return TYPES;
	}


	public Class returnedClass() {
		return List.class;
	}

	public boolean equals(Object object, Object object1) throws HibernateException {
		if (object == object1) {
			return true;
		}
		if (object != null && object1 != null) {
			List set0 = (List) object;
			List set1 = (List) object1;
			if (set0.size() != set1.size()) {
				return false;
			}
			Object[] s0 = set0.toArray();
			Object[] s1 = set1.toArray();
			if (s0.length != s1.length) {
				return false;
			}
			for (int i = 0; i < s0.length; i++) {
				String id0 = (String) s0[i];
				String id1 = (String) s1[i];
				if (!id0.equals(id1)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public int hashCode(Object object) throws HibernateException {
		List s = (List) object;
		return s.hashCode();
	}


	public Object nullSafeGet(ResultSet resultSet, String[] stringArray, Object object) throws HibernateException,
			SQLException {
		String value = (String) Hibernate.STRING.nullSafeGet(resultSet, stringArray[0]);
		if (value != null) {
			return parse(value);
		} else {
			return new ArrayList();
		}

	}

	public void nullSafeSet(PreparedStatement preparedStatement, Object object, int _int) throws HibernateException,
			SQLException {
		if (object != null) {
			String str = assemble((List) object);
			Hibernate.STRING.nullSafeSet(preparedStatement, str, _int);
		} else {
			Hibernate.STRING.nullSafeSet(preparedStatement, "", _int);
		}
	}

	@SuppressWarnings("unchecked")
	public Object deepCopy(Object object) throws HibernateException {
		List sourceSet = (List) object;
		List targetSet = new ArrayList();
		if (sourceSet != null) {
			targetSet.addAll(sourceSet);
		}
		return targetSet;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object object) throws HibernateException {
		return (Serializable) deepCopy(object);
	}

	public Object assemble(Serializable serializable, Object object) throws HibernateException {
		return deepCopy(serializable);
	}

	public Object replace(Object object, Object object1, Object object2) throws HibernateException {
		return object;
	}

	private String assemble(List l) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < l.size(); i++) {
			sb.append((String) l.get(i));
			sb.append(SPLITTER);
		}
		String fs = sb.toString();
		if (fs != null && fs.length() > 0 && fs.endsWith(SPLITTER)) {
			fs = fs.substring(0, fs.length() - 1);
		}
		return fs;
	}

	@SuppressWarnings("unchecked")
	private List parse(String value) {
		String[] strs = StringUtils.split(value, SPLITTER);
		List set = new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			if (!StringUtils.isBlank(strs[i])) {
				set.add(strs[i]);
			}
		}
		return set;
	}

}

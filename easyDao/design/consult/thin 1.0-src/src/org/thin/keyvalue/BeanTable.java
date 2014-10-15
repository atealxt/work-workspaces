package org.thin.keyvalue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.thin.keyvalue.criteria.Criterion;
import org.thin.keyvalue.criteria.GroupBy;
import org.thin.keyvalue.criteria.Junction;
import org.thin.keyvalue.criteria.OrderBy;
import org.thin.keyvalue.criteria.Pagination;
import org.thin.keyvalue.criteria.SQLCriterion;

/**
 * BeanTable ��һ�������࣬������SingleBeanTable��JoinedBeanTable��MultiBeanTable�Ǹÿ��(Thin)<br/>
 * �����࣬�ֱ��ṩ�Ե���˫��(join)����Ĺ���������BeanTable�����������Ϊ���ݿ����Ӱ�䣬
 * ͨ������beanTable���������ݿ⣬�����ھ�ͳһΪMap��key-value��ʽ��
 * 
 * �����ṩ�˻�����ѯ���ܣ�where��group by��order by �ȹ���������
 * 
 * @author Haihong.Wang
 * @version Feb 19, 2010
 */
public abstract class BeanTable {

	protected static Logger log = Logger.getLogger(BeanTable.class);

	protected String renderGroupBy(List<Criterion> criterions) {

		String groupby = "";
		if (criterions != null && criterions.size() > 0) {
			for (int index = 0; index < criterions.size(); index++) {
				if (criterions.get(index).getClass() == GroupBy.class) {
					groupby = criterions.get(index).toString();
					break;
				}
			}
		}
		return groupby;
	}

	protected String renderOrderBy(List<Criterion> criterions) {

		String orderby = "";
		if (criterions != null && criterions.size() > 0) {
			for (int index = 0; index < criterions.size(); index++) {
				if (criterions.get(index).getClass() == OrderBy.class) {
					orderby = criterions.get(index).toString();
					break;
				}
			}
		}
		return orderby;
	}

	protected String renderWhere(List<Criterion> criterions) {
		String where = "";
		if (criterions != null && criterions.size() > 0) {
			
			for (int index = 0; index < criterions.size(); index++) {
				if (criterions.get(index).getClass() == SQLCriterion.class) {
					if("".equals(where)){
						where = " WHERE ";
					}
					where = where + " " + criterions.get(index).toString();
					if (index + 1 < criterions.size()) {
						if (criterions.get(index + 1).getClass() != Junction.class) {
							where = where + " AND ";
						}
					}
				} else if (criterions.get(index).getClass() == Junction.class) {
					if("".equals(where)){
						where = " WHERE ";
					}
					where = where + " " + criterions.get(index).toString();
				}
			}
		}
		return where;
	}

	public List<Map<String, Object>> get(List<String> wantedColumns,
			Criterion... criterions) throws Exception {
		return this.get(wantedColumns, Arrays.asList(criterions));
	}

	public List<Map<String, Object>> get(String[] wantedColumns,
			Criterion... criterions) throws Exception {
		return this
				.get(Arrays.asList(wantedColumns), Arrays.asList(criterions));
	}

	public List<Map<String, Object>> get(Criterion... criterions)
			throws Exception {
		return this.get(Arrays.asList(new String[] {}),
				Arrays.asList(criterions));
	}

	public List<Map<String, Object>> get() throws Exception {
		return this.get(Arrays.asList(new String[] {}),
				new ArrayList<Criterion>(0));
	}

	protected Pagination hasPagingation(List<Criterion> criterions) {
		for (int i = 0; i < criterions.size(); i++) {
			Criterion c = criterions.get(i);
			if (c.getClass()==Pagination.class) {
				return (Pagination) c;
			}
		}
		return null;
	}

	/**
	 * �������ݿ�Ĳ�ͬ�Լ����
	 * @param sql
	 * @param pageInfo
	 * @return
	 * @throws SQLException
	 */
	public String pagingSQL(String sql, Pagination pageInfo)
			throws SQLException {
		String pname = DBAccesser.getDatabaseProductName();
		if ("MySQL".equalsIgnoreCase(pname)) {
			sql = sql + " LIMIT " + (pageInfo.getBegin()-1) + ","
					+ (pageInfo.getEnd()-1);
		} else if ("oracle".equalsIgnoreCase(pname)) {
			sql = "SELECT * FROM (SELECT A.*,ROWNUM R FROM (" + sql
					+ ") A ) B WHERE  B.R<=" + pageInfo.getEnd() + " AND B.R>"
					+ pageInfo.getBegin();

		} else if ("DB2".equalsIgnoreCase(pname)) {
			// to do it yourself
		} else if ("SQL server".equalsIgnoreCase(pname)) {
			// to do it yourself
		}
		return sql;
	}

	public abstract List<Map<String, Object>> get(List<String> wantedColumns,
			List<Criterion> criterions) throws Exception;

	protected abstract String allColumns();

}

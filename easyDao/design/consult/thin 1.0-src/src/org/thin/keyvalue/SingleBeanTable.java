package org.thin.keyvalue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.thin.keyvalue.criteria.Criterion;
import org.thin.keyvalue.criteria.Pagination;
import org.thin.keyvalue.criteria.SQLCriterion;
import org.thin.keyvalue.join.JoinType;
import org.thin.keyvalue.join.JoinedBeanTable;

/**
 * 单表操作
 * @author Haihong.Wang
 * @version Feb 19, 2010
 */
public class SingleBeanTable extends BeanTable {

	private String tableName;

	private boolean initialized;

	/**
	 * 初始化时将数据库的字段名称和类型，假如columnNameType
	 */
	private Map<String, String> columnNameType = new HashMap();

	/**
	 * 初始化时将数据库主键的字段名称和类型，假如keyColumns， 可以是单一主键也可以是复合主键
	 */
	private Map<String, String> keyColumns = new HashMap();

	private SingleBeanTable(String tableName) {
		this.tableName = tableName;

	}

	public static SingleBeanTable instance(String tableName) throws SQLException {
		SingleBeanTable beanTable = new SingleBeanTable(tableName);
		InitTools.INSTANCE.loadTableProfile(beanTable);
		beanTable.initialized = true;
		return beanTable;

	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public void add(Map<String, Object> keyValue) throws Exception {

		Iterator iter = keyValue.entrySet().iterator();

		List<String> columns = new ArrayList();
		List<Object> values = new ArrayList();
		StringBuffer strValues = new StringBuffer();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String colName = (String) entry.getKey();

			colName = colName.toUpperCase();

			Object value = entry.getValue();
			if (this.columnNameType.containsKey(colName)) {
				columns.add(colName);
				values.add(formatValue(colName, value));
				strValues.append("?,");
			}

		}

		String sql = "INSERT INTO " + this.tableName
				+ " (:columns) VALUES (:values)";

		String columnsStr = columns.toString();

		columnsStr = columnsStr.substring(1, columnsStr.length() - 1);

		sql = sql.replace(":columns", columnsStr);

		sql = sql.replace(":values", strValues.substring(0,
				strValues.length() - 1));

		DBAccesser.INSTANCE.executeSQL(sql, values);

	}

	private Object formatValue(String columnName, Object value) {
		String type = this.columnNameType.get(columnName.toUpperCase());
		try {
			if (value == null) {
				return value;
			}

			if (type.indexOf("CHAR") >= 0) {
				return value.toString();
			} else if (type.indexOf("BIGINT") >= 0) {
				return value.getClass() == Long.class ? value : Long
						.parseLong((String) value);
			} else if (type.indexOf("INT") >= 0) {
				return value.getClass() == Integer.class ? value : Integer
						.parseInt((String) value);
			} else if (type.indexOf("FLOAT") >= 0) {
				return value.getClass() == Float.class ? value : Float
						.parseFloat((String) value);
			} else if (type.indexOf("DECIMAL") >= 0) {
				return value.getClass() == Double.class ? value : Double
						.parseDouble((String) value);
			} else if (type.indexOf("DATE") >= 0) {
				return java.util.Date.class.isAssignableFrom(value.getClass()) ? value
						: parseDate(value);
			}
		} catch (Exception e) {
			log.error(
					"column type converting error! please check column-type-value:["
							+ columnName + "-" + type + "-" + value + "]", e);
			throw new RuntimeException(e);
		}

		return value.toString();
	}

	private Object parseDate(Object value) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse((String)value);
	}

	public List<Map<String, Object>> get(List<Criterion> criterions)
			throws Exception {

		String sql = "SELECT " + allColumns() + " FROM " + this.tableName
				+ this.renderWhere(criterions);

		Pagination page = this.hasPagingation(criterions);
		if (page != null) {
			sql = pagingSQL(sql, page);
		}
		return DBAccesser.INSTANCE.executeQuery(sql);

	}

	protected String allColumns() {
		String cols = this.columnNameType.keySet().toString();
		return cols = cols.substring(1, cols.length() - 1);
	}

	public List<Map<String, Object>> get(List<String> wantedColumns,
			List<Criterion> criterions) throws Exception {
		String cols = "";
		if (wantedColumns != null && wantedColumns.size() > 0) {
			cols = wantedColumns.toString();
			cols = cols.substring(1, cols.length() - 1);
		} else {
			cols = this.allColumns();
		}

		String sql = "SELECT " + cols + " FROM " + this.tableName
				+ this.renderWhere(criterions) + " " + this.renderGroupBy(criterions)
				+ " " + this.renderOrderBy(criterions);
		
		Pagination page = this.hasPagingation(criterions);
		if(page!=null){
			sql = this.pagingSQL(sql, page);
		}
		
		return DBAccesser.INSTANCE.executeQuery(sql);
	}

	public boolean update(Map<String, Object> keyValue) throws Exception {

		List<Criterion> criterions = new ArrayList();

		Iterator iter = this.keyColumns.entrySet().iterator();

		keyValue = toUpperCaseKey(keyValue);

		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();

			Object value2 = keyValue.get(key);
			if (value2 != null) {
				criterions.add(SQLCriterion.get(key, value2));
				keyValue.remove(key);
			}

		}

		if (criterions.isEmpty()) {
			throw new Exception(
					"the 'keyValue' do not contain primary key!");
		}

		return this.update(keyValue, criterions);

	}

	private Map<String, Object> toUpperCaseKey(Map<String, Object> keyValue) {

		Map newMap = new HashMap();
		Iterator it = keyValue.entrySet().iterator();

		while (it.hasNext()) {
			Entry e = (Entry) it.next();
			String key = e.getKey().toString().toUpperCase();
			newMap.put(key, e.getValue());
		}
		return newMap;
	}

	public boolean update(Map<String, Object> keyValue,
			List<Criterion> criterions) throws Exception {
		String sql = "UPDATE " + this.tableName + " SET :keyValue "
				+ this.renderWhere(criterions);

		String keyvalue = "";
		Iterator iter = keyValue.entrySet().iterator();
		List<Object> values = new ArrayList<Object>();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) iter
					.next();
			String key = entry.getKey();
			if(this.columnNameType.containsKey(key)){
				keyvalue = keyvalue + key + "=?,";
				Object value = entry.getValue();
				values.add(this.formatValue(key, value));
			}
			
		}

		keyvalue = keyvalue.substring(0, keyvalue.length() - 1);

		sql = sql.replaceFirst(":keyValue", keyvalue);

		return DBAccesser.INSTANCE.executeSQL(sql, values);

	}

	public void delete(List<Criterion> criterions) throws Exception {
		String sql = "DELETE FROM " + this.tableName + " "
				+ this.renderWhere(criterions);
		DBAccesser.INSTANCE.executeSQL(sql);
	}

	public void delete(Criterion... criterions) throws Exception {
		this.delete(Arrays.asList(criterions));
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getColumnNameType() {
		return columnNameType;
	}

	public void setColumnNameType(Map<String, String> columnNameType) {
		this.columnNameType = columnNameType;
	}

	public Map<String, String> getKeyColumns() {
		return keyColumns;
	}

	public void setKeyColumns(Map<String, String> keyColumns) {
		this.keyColumns = keyColumns;
	}

	public void addColumnNameType(String fieldName, String type) {
		this.columnNameType.put(fieldName, type);
	}

	public void addPrimarys(String columnName) {
		this.keyColumns.put(columnName, this.columnNameType.get(columnName));
	}

	/**
	 * 关联查询 left join 
	 * @param beanTable
	 * @return JoinedBeanTable
	 */
	public JoinedBeanTable leftJoin(SingleBeanTable beanTable) {
		return new JoinedBeanTable(this, JoinType.LEFT, beanTable);
	}

	/**
	 * 关联查询 right join
	 * @param beanTable
	 * @return JoinedBeanTable
	 */
	public JoinedBeanTable rightJoin(SingleBeanTable beanTable) {
		return new JoinedBeanTable(this, JoinType.RIGHT, beanTable);
	}
	
	public String toString() {
		return "tableName: " + this.tableName.toUpperCase() + ", column-type: "
				+ this.columnNameType + ", primary-column-type: "
				+ this.keyColumns;
	}

}

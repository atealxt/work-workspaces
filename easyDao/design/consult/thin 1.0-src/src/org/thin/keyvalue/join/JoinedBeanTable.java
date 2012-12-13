package org.thin.keyvalue.join;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.thin.keyvalue.BeanTable;
import org.thin.keyvalue.SingleBeanTable;
import org.thin.keyvalue.DBAccesser;
import org.thin.keyvalue.criteria.Criterion;
/**
 * 使用于两张表左右关联查询，如：<BR/>
 * SELECT * FROM table1 a LEFT JOIN table2 b ON a.id=b.id 
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class JoinedBeanTable extends BeanTable {

	private SingleBeanTable beanTable;
	private JoinType joinType;
	private SingleBeanTable beanTable2;

	private String column;
	private String column2;

	public JoinedBeanTable(SingleBeanTable beanTable, JoinType joinType,
			SingleBeanTable beanTable2) {
		this.beanTable = beanTable;
		this.joinType = joinType;
		this.beanTable2 = beanTable2;
	}

	public JoinedBeanTable on(String column, String column2) {
		this.column = column;
		this.column2 = column2;
		return this;
	}

	public String toString() {
		return this.beanTable.getTableName()+" "+ this.joinType+" JOIN "+this.beanTable2+" ON "+this.column+"="+this.column2;
	}

	public List<Map<String,Object>> get(List<String> wantedColumns, List<Criterion> criterions) throws Exception {
		String cols = "";
		if (wantedColumns != null && wantedColumns.size() > 0) {
			cols = wantedColumns.toString();
			cols = cols.substring(1, cols.length() - 1);
		} else {
			cols = this.allColumns();
		}

		String sql = "SELECT " + cols + " FROM "
				+ this.beanTable.getTableName() + " " + this.joinType
				+ " JOIN " + this.beanTable2.getTableName() + " ON "
				+ this.beanTable.getTableName() + "." + this.column + "="
				+ this.beanTable2.getTableName() + "." + this.column2 + " "
				+ this.renderWhere(criterions) + " " + this.renderGroupBy(criterions)
				+ " " + this.renderOrderBy(criterions);

		return DBAccesser.INSTANCE.executeQuery(sql);
	}

	protected String allColumns() {
		Iterator iter = this.beanTable.getColumnNameType().keySet().iterator();
		StringBuffer sb = new StringBuffer();

		while (iter.hasNext()) {
			String column = (String) iter.next();
			sb.append(this.beanTable.getTableName() + "." + column + ",");
		}

		iter = this.beanTable2.getColumnNameType().keySet().iterator();

		while (iter.hasNext()) {
			String column = (String) iter.next();
			sb.append(this.beanTable2.getTableName() + "." + column + ",");
		}

		String str = sb.toString();
		str = str.substring(0, str.length() - 1);
		return str;
	}

}

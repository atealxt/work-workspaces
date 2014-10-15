package org.thin.keyvalue.join;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.thin.keyvalue.BeanTable;
import org.thin.keyvalue.SingleBeanTable;
import org.thin.keyvalue.DBAccesser;
import org.thin.keyvalue.criteria.Criterion;

/**
 * 用于多表关联查询，如：<br/>
 * SELECT a.*,b.*,c.* FROM user a,group b,role c WHERE a.groupid=b.id and a.roleid=c.id
 * 
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class MultiBeanTable extends BeanTable{

	
	private Map<String,SingleBeanTable> beanTables = new HashMap();
	
	private List<String> connectors = new ArrayList();
	
	public MultiBeanTable addBeanTable(String alias,SingleBeanTable beanTable){
		this.beanTables.put(alias, beanTable);
		return this;
	}
	
	
	/**
	 * 
	 * @param connector must be <code>a.group_id=b.group_id</code>
	 */
	public MultiBeanTable addConnecter(String connector){
		this.connectors.add(connector);
		return this;
	}
	
	public List<Map<String,Object>> get(List<String> wantedColumns, List<Criterion> criterions) throws Exception {
		String cols = "";
		if (wantedColumns != null && wantedColumns.size() > 0) {
			cols = wantedColumns.toString();
			cols = cols.substring(1, cols.length() - 1);
		} else {
			cols = this.allColumns();
		}

		String where = this.renderWhere(criterions);
		
		if("".equals(where)){
			where = " WHERE "+this.allConnectors();
		}else{
			where = where + " AND  "+this.allConnectors();
		}
		String sql = "SELECT "+cols+ " FROM "+this.allTables()+" "+where+" "+this.renderGroupBy(criterions)+" "+this.renderOrderBy(criterions);
		
		return DBAccesser.INSTANCE.executeQuery(sql);
	}

	
	
	private String allTables() {
		
		Iterator btsIter = this.beanTables.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		
		while(btsIter.hasNext()){
			Entry entry = (Entry)btsIter.next();
			String alias = (String)entry.getKey();
			SingleBeanTable bt = (SingleBeanTable)entry.getValue();
			sb.append(bt.getTableName()+" "+alias+",");
		}
		String str = sb.toString();
		str = str.substring(0, str.length() - 1);
		return str;
	}

	private String allConnectors() {
		Iterator it = this.connectors.iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext()){
			sb.append(it.next()+",");
		}
		String str = sb.toString();
		str = str.substring(0, str.length() - 1);
		return str;
	}

	protected String allColumns() {
		Iterator btsIter = this.beanTables.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		
		while(btsIter.hasNext()){
			Entry entry = (Entry)btsIter.next();
			String alias = (String)entry.getKey();
			SingleBeanTable bt = (SingleBeanTable)entry.getValue();
			
			Iterator iter = bt.getColumnNameType().keySet().iterator();
			
			while (iter.hasNext()) {
				String column = (String) iter.next();
				sb.append(alias + "." + column + ",");
			}
			
		}

		String str = sb.toString();
		str = str.substring(0, str.length() - 1);
		return str;
	}
}

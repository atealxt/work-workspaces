package org.thin.keyvalue;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 该类是BeanTable的工厂类，通过表名获取对应的BeanTable。
 * @author Haihong.Wang
 * @version Feb 19, 2010
 */
public class BeanTableManager {
	
	protected static Logger log = Logger.getLogger(BeanTableManager.class);
	
	private static Map<String,SingleBeanTable> beanTables = new ConcurrentHashMap();
	
	/**
	 * 
	 * @param tableName
	 * @return SingleBeanTable,如果数据库没有该表返回null。
	 * @throws SQLException
	 */
	public static SingleBeanTable getBeanTable(String tableName) throws SQLException{
		SingleBeanTable bt = beanTables.get(tableName);
		if(bt==null){
			bt = SingleBeanTable.instance(tableName);
			log.info(bt.getTableName()+" has instanced!");
			beanTables.put(tableName, bt);
		}
		return bt;
	}
	
	
	

}

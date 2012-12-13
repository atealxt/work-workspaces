package org.thin.keyvalue;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * ������BeanTable�Ĺ����࣬ͨ��������ȡ��Ӧ��BeanTable��
 * @author Haihong.Wang
 * @version Feb 19, 2010
 */
public class BeanTableManager {
	
	protected static Logger log = Logger.getLogger(BeanTableManager.class);
	
	private static Map<String,SingleBeanTable> beanTables = new ConcurrentHashMap();
	
	/**
	 * 
	 * @param tableName
	 * @return SingleBeanTable,������ݿ�û�иñ���null��
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

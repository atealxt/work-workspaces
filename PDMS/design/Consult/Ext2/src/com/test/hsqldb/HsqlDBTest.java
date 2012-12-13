/**
 * 
 */
package com.test.hsqldb;

import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class HsqlDBTest {

	public static void main(String[] args) {
		
		HsqlDBUtil db = new HsqlDBUtil();
		
		String sql4 = "select * from T_SYS_RESOURCE";
		List list = db.queryForList(sql4);
		for(int i=0;i<list.size();i++){
			Map m = (Map)list.get(i);
			System.out.println(m.get("R_NAME"));
		}
		db.colseDb();
		
	}
}

package org.thin.keyvalue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.thin.common.Prt;
import org.thin.keyvalue.criteria.Criterion;
import org.thin.keyvalue.criteria.Junction;
import org.thin.keyvalue.criteria.Operator;
import org.thin.keyvalue.criteria.Pagination;
import org.thin.keyvalue.criteria.SQLCriterion;


/**
 * 
 * @author Haihong.Wang
 * @version Feb 21, 2010
 */
public class TestBeanTable extends TestCase {
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		DBAccesser.cleanConnection();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bt = SingleBeanTable.instance("z_user");
	}
	
	SingleBeanTable bt;
	
//	public void testSpeech() throws Exception{
//		DBAccesser.setShowSQL(false);
//		long start = System.currentTimeMillis();
//		DBAccesser.beginTransaction();
//		for(int i=0;i<10000;i++){
//			
//			Map keyValue = new HashMap();
//			keyValue.put("userid", "wanghh"+i);
//			keyValue.put("password", "jiyinan");
//			keyValue.put("age", 23);
//			keyValue.put("username", "王海洪");
//			keyValue.put("birthday", new Date());
//			keyValue.put("group_id", "651080002");
//			keyValue.put("role_id", "admin");
//			bt.add(keyValue);
//			
//			
//		}
//		DBAccesser.commitTransaction();
//		long end = System.currentTimeMillis();
//		
//		Prt.prtln("共耗时："+(end-start)+" 毫秒");
//	}
	
	public void testadd() throws Exception{
		Map keyValue = new HashMap();
		keyValue.put("userid", "wanghh");
		keyValue.put("password", "jiyinan");
		keyValue.put("age", 23);
		keyValue.put("username", "王海洪");
		keyValue.put("birthday", new Date());
		keyValue.put("group_id", "651080002");
		keyValue.put("role_id", "admin");
		bt.add(keyValue);
		
		keyValue = new HashMap();
		keyValue.put("userid", "tracy");
		keyValue.put("password", "wanghh");
		keyValue.put("age", 23);
		keyValue.put("username", "老婆");
		keyValue.put("birthday", new Date());
		keyValue.put("group_id", "651080001");
		keyValue.put("role_id", "guest");
		bt.add(keyValue);
		
		keyValue = new HashMap();
		keyValue.put("userid", "snail");
		keyValue.put("password", "wanghh");
		keyValue.put("age", 23);
		keyValue.put("username", "女朋友");
		keyValue.put("birthday", new Date());
		keyValue.put("group_id", "651080001");
		keyValue.put("role_id", "guest");
		bt.add(keyValue);
	}
	
	
	
	public void testupdate() throws Exception{
		Map keyValue = new HashMap();
		keyValue.put("userid", "wanghh");
		keyValue.put("password", "tracy");
		keyValue.put("age", 26);
		keyValue.put("username", "王海洪");
		keyValue.put("birthday", new Date());
		keyValue.put("group_id", "65102");
		keyValue.put("role_id", "admin");
		bt.update(keyValue);
		
	}
	
	public void testGet() throws Exception{
		Prt.prtln(bt.get(SQLCriterion.get("username",Operator.LIKE,"%洪%")));
	}
	
	public void testGet2() throws Exception{
		List<Criterion> where = new ArrayList();
		where.add(SQLCriterion.get("username",Operator.LIKE,"%洪%"));
		where.add(Junction.OR);
		where.add(SQLCriterion.get("age", Operator.EQ, 23));
		Prt.prtln(bt.get(where));
	}
	
	
	public void testWantedGet() throws Exception{
		List<Criterion> where = new ArrayList();
		where.add(SQLCriterion.get("username",Operator.LIKE,"%洪%"));
		where.add(Junction.OR);
		where.add(SQLCriterion.get("age", Operator.EQ, 23));
		
		
		List wantedColumns = new ArrayList();
		wantedColumns.add("userid");
		wantedColumns.add("password");
		Prt.prtln(bt.get(wantedColumns,where));
	}
	
	public void testWantedGet2() throws Exception{
		List<Criterion> where = new ArrayList();
		where.add(SQLCriterion.get("username",Operator.LIKE,"%洪%"));
		where.add(Junction.OR);
		where.add(SQLCriterion.get("age", Operator.EQ, 23));
		
		
		List wantedColumns = new ArrayList();
		wantedColumns.add("sum(age) as totalAge");
		
		Prt.prtln(bt.get(wantedColumns,where));
	}
	
	public void testDelete1() throws Exception{
		bt.delete(SQLCriterion.get("userid",Operator.EQ,"wanghh"));
	}
	
	
	public void testDelete2() throws Exception{
		List<Criterion> where = new ArrayList();
		where.add(SQLCriterion.get("username",Operator.LIKE,"%洪%"));
		where.add(Junction.OR);
		where.add(SQLCriterion.get("age", Operator.EQ, 23));
		bt.delete(where);
	}
	
	public void testPagination(){
		Pagination p = new Pagination();
		p.setPageIndex(1);
		p.setPageSize(2);
		try {
			BeanTable bt = BeanTableManager.getBeanTable("z_user");
			Prt.prtln(bt.get(p));
			Prt.prtln(bt.get(new String[]{"count(*)"}, p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void testPaginationOracle(){
		Pagination p = new Pagination();
		p.setPageIndex(1);
		p.setPageSize(2);
		try {
			BeanTable bt = BeanTableManager.getBeanTable("sys_batch_record");
			Prt.prtln(bt.get(p));
			Prt.prtln(bt.get(new String[]{"count(*)"}));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

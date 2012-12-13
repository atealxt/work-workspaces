package org.thin.keyvalue.join;

import junit.framework.TestCase;

import org.thin.keyvalue.DBAccesser;
import org.thin.keyvalue.SingleBeanTable;
import org.thin.keyvalue.criteria.SQLCriterion;

public class TestMultiBeanTable extends TestCase {

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		DBAccesser.cleanConnection();
	}

	
	public void testGet() throws Exception{
		SingleBeanTable userT  = SingleBeanTable.instance("z_user");
		SingleBeanTable groupT = SingleBeanTable.instance("z_group");
		
		MultiBeanTable mbt = new MultiBeanTable();
		
		mbt.addBeanTable("a", userT);
		mbt.addBeanTable("b",groupT);
		
		mbt.addConnecter("a.group_id=b.group_id");
		mbt.get();
	}
	
	
	public void testGet2() throws Exception{
		SingleBeanTable userT  = SingleBeanTable.instance("z_user");
		SingleBeanTable groupT = SingleBeanTable.instance("z_group");
		
		MultiBeanTable mbt = new MultiBeanTable();
		
		mbt.addBeanTable("a",userT).addBeanTable("b",groupT).addConnecter("a.group_id=b.group_id");
		
		mbt.get(new String[]{"a.username","b.group_name"}, SQLCriterion.get("role_id", "guest"));
	}
	
}

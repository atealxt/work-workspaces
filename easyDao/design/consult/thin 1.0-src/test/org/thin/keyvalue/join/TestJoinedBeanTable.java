package org.thin.keyvalue.join;

import junit.framework.TestCase;

import org.thin.common.Prt;
import org.thin.keyvalue.DBAccesser;
import org.thin.keyvalue.SingleBeanTable;
import org.thin.keyvalue.criteria.Operator;
import org.thin.keyvalue.criteria.SQLCriterion;

public class TestJoinedBeanTable extends TestCase {

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		DBAccesser.cleanConnection();
	}

	
	public void testAll() throws Exception{
		SingleBeanTable user = SingleBeanTable.instance("z_user");
		SingleBeanTable group = SingleBeanTable.instance("z_group");
		JoinedBeanTable jbt = user.leftJoin(group).on("group_id", "group_id");
	
		Prt.prtln(jbt.get(SQLCriterion.get("role_id", Operator.EQ, "guest")));
		
		jbt = user.rightJoin(group).on("group_id", "group_id");
		
		Prt.prtln(jbt.get(SQLCriterion.get("role_id", Operator.EQ, "guest")));
		
	}
}

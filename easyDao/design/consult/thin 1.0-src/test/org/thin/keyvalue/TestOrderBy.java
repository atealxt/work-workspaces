package org.thin.keyvalue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.thin.common.Prt;
import org.thin.keyvalue.criteria.Order;
import org.thin.keyvalue.criteria.OrderBy;

import junit.framework.TestCase;

public class TestOrderBy extends TestCase{

	public void testOrderBy(){
		Map orderItems= new LinkedHashMap();
		
		orderItems.put("1", "one");
		
		orderItems.put("3", "three");
		
		orderItems.put("2", "two");
		
		Prt.prtln(orderItems);
		
		Prt.prtln(OrderBy.asc("userid").append("username", Order.DESC).toString());
	}
}

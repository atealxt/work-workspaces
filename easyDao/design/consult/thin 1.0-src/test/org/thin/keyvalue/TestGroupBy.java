package org.thin.keyvalue;

import junit.framework.TestCase;

import org.thin.common.Prt;
import org.thin.keyvalue.criteria.GroupBy;

public class TestGroupBy extends TestCase{

	public void testGroupBy(){
		Prt.prtln(GroupBy.by("username"));
	}
	
	public void testGroupByMuti(){
		Prt.prtln(GroupBy.by("username","age"));
	}
}

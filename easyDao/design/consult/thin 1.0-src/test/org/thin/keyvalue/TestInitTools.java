package org.thin.keyvalue;

import java.sql.SQLException;

import org.thin.common.Prt;

import junit.framework.TestCase;

public class TestInitTools extends TestCase {

	public void testAll() throws SQLException{
		BeanTable bt = SingleBeanTable.instance("z_user");
		Prt.prtln(bt);
	}
}

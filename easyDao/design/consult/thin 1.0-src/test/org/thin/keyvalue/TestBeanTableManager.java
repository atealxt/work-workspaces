package org.thin.keyvalue;


import org.thin.common.Prt;

import junit.framework.TestCase;

public class TestBeanTableManager extends TestCase{

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		DBAccesser.cleanConnection();
	}

	
	public void testSave() throws Exception{
		try {
			BeanTable userBean = BeanTableManager.getBeanTable("z_user");
			Prt.prtln(userBean);
			BeanTable groupBean = BeanTableManager.getBeanTable("z_group");
			Prt.prtln(groupBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

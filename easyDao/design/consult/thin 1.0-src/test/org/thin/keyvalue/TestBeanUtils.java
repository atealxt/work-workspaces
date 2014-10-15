package org.thin.keyvalue;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import junit.framework.TestCase;

import org.thin.common.Prt;
import org.thin.keyvalue.utils.ThinUtils;

public class TestBeanUtils extends TestCase {

	public void testBeanToMap() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		User user = new User();
		user.setId("wanghh");
		user.setName("º£ºé");
		user.setGroupId("651080001");
		Map map = ThinUtils.beanToMap(user);
		
		Prt.prtln(map);
	}
	
	public void testBeanToBeanTableMap() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		User user = new User();
		user.setId("wanghh");
		user.setName("º£ºé");
		user.setGroupId("651080001");
		Map map = ThinUtils.beanToMap(user);
		
		Prt.prtln(map);
	}
	
	
}

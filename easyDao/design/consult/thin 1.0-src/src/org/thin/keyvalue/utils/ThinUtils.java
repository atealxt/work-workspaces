package org.thin.keyvalue.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * 这是辅助类在thin中并没有实际需要，但是在实际开发中，如果要将bean持久到数据库中
 * 就需要把bean转换成Map，或者将map转换成bean。
 * 
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class ThinUtils {
	

	public static Map<String,Object> beanToMap(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String,Object> keyvalues= new HashMap();
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for(Field field: fields){
			Object value = PropertyUtils.getProperty(bean, field.getName());
			keyvalues.put(field.getName(), value);
		}
		
		return keyvalues;
	}
	
	public static Map<String,Object> beanToBeanTableMap(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String,Object> keyvalues= new HashMap();
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for(Field field: fields){
			Object value = PropertyUtils.getProperty(bean, field.getName());
			String fieldName = formatFieldName(field.getName());
			keyvalues.put(fieldName, value);
		}
		
		return keyvalues;
	}
	
	
	private static String formatFieldName(String name) {
		StringBuffer sb = new StringBuffer(name);
		StringBuffer dest = new StringBuffer();
		for(int i=0;i<sb.length();i++){
			char ch = sb.charAt(i);
			if(Character.isUpperCase(ch)){
				dest.append('_');
				dest.append(ch);
			}else{
				dest.append(Character.toUpperCase(ch));
			}
		}
		
		return dest.toString();
	}

	public static Map<String,Object> formatMap4Thin(Map<String,Object> oldMap){
		Map<String,Object> keyvalues= new HashMap();
		Iterator iter = oldMap.entrySet().iterator();
		while(iter.hasNext()){
			Entry e = (Entry)iter.next();
			keyvalues.put(formatFieldName((String)e.getKey()),e.getValue());
		}
		return keyvalues;
	}
	
}

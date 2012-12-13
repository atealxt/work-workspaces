package com.m2.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.m2.common.Constant;

public class Util {
	
	public static boolean isNotBlank(String str){
		if ((str!=null)&&(!"".equalsIgnoreCase(str)))
			return true;
		return false;
		
	}
	
	
	public static String formatDate(Date date,String style) {
		SimpleDateFormat format = new SimpleDateFormat(style);
		return format.format(date);
	}
	
	public static String formatDateForLong(long date,String style) {
		SimpleDateFormat format = new SimpleDateFormat(style);
		return format.format(date);
	}	


	public static String formatDateTime(Date date,String style) {
		SimpleDateFormat format = new SimpleDateFormat(style);
		return format.format(date);
	}	
	
	public static String formatDateTimeForLong(Date date,String style) {
		SimpleDateFormat format = new SimpleDateFormat(style);
		return format.format(date);
	}		
	
	
	
	public static int parseInt(String data){
		try{
			return Integer.valueOf(data);
		}catch(NumberFormatException e){
			return -1;
		}
	}
	
	
	public static String getActionURLWithoutSuffix(String action) {

		StringBuffer value = new StringBuffer();

		
		String servletMapping = Constant.URL_STYLE;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingNameWithoutSuffix(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}	
		return (value.toString());
	}
	
	public static String getActionMappingNameWithoutSuffix(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		return (value);
	}	
	

}

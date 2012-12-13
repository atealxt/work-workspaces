/**
 * 
 */
package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class PropertiesUtil {

	public static Properties getDatebaseProperties() {
		
			InputStream in = PropertiesUtil.class.getResourceAsStream(
					"/database.properties");
			Properties varp = new Properties();
			try {
				varp.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return varp;

	}
	
	public static Properties getConfigProperties() {
		
		InputStream in = PropertiesUtil.class.getResourceAsStream(
				"/com/test/gen/conf.properties");
		Properties varp = new Properties();
		try {
			varp.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return varp;

	}
	
	public static String getProperty(Properties varp,String name,String encode){
		try {
			return new String(varp.getProperty(name).getBytes("ISO-8859-1"),encode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

/**
 * 
 */
package com.test.gen;

import java.util.List;
import java.util.Properties;

import com.test.gen.impl.GenService;
import com.test.gen.impl.ZhuSrcService;
import com.test.gen.interfc.IGenService;
import com.test.gen.interfc.IZhuSrcService;
import com.test.util.TableColumns;
import com.test.util.VelocityUtil;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public class GenFactory {
	
	private static GenFactory instance = new GenFactory();

	/**
	 * ���� ���췽��
	 */	
	private GenFactory() {
	}

	/**
	 * ���� ���췽��
	 * @return
	 */
	public static GenFactory getinstance() {
		return instance;
	}
	
	public IGenService getGenService(){
		return new GenService();
	}
	
	public IZhuSrcService getZhuSrcService(Properties varp,List<TableColumns> list,VelocityUtil agt){
		return new ZhuSrcService(varp,list,agt);
	}

}

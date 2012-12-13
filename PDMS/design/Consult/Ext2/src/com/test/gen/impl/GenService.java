/**
 * 
 */
package com.test.gen.impl;

import java.util.List;
import java.util.Properties;

import com.test.gen.GenFactory;
import com.test.gen.interfc.IGenService;
import com.test.gen.interfc.IZhuSrcService;
import com.test.util.GetDBInfo;
import com.test.util.PropertiesUtil;
import com.test.util.TableColumns;
import com.test.util.VelocityUtil;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class GenService implements IGenService {

	public void genZhuFiles() {
		try {
			VelocityUtil agt = new VelocityUtil();  
            agt.init();
            //获取配置资源
            //InputStream in = AutoGen.class.getResourceAsStream("conf.properties");   
            Properties varp = PropertiesUtil.getConfigProperties(); 	                            
            //获取数据库信息
			GetDBInfo db = new GetDBInfo();
			List<TableColumns> list = db.getDbTableInfo(varp.getProperty("table_name"),false);
			
			
			IZhuSrcService ss = GenFactory.getinstance().getZhuSrcService(varp, list, agt);
			ss.genJavaBean();
			System.out.println("JavaBean gen success");
			ss.genIDAO();
			System.out.println("IDAO gen success");
			ss.genDAOImpl();
			System.out.println("DAOImpl gen success");
			ss.genIService();
			System.out.println("IService gen success");
			ss.genServiceImpl();
			System.out.println("ServiceImpl gen success");
			ss.genIbatisXml();
			System.out.println("IbatisXML gen success");
			ss.genAction();
			System.out.println("Action gen success");
			ss.genConfig();
			System.out.println("Config gen success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}

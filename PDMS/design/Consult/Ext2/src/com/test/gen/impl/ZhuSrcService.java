/**
 * 
 */
package com.test.gen.impl;

import java.util.List;
import java.util.Properties;

import com.test.gen.interfc.IZhuSrcService;
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
public class ZhuSrcService implements IZhuSrcService {

	private Properties varp;
	
	private List<TableColumns> list;
	
	private VelocityUtil agt;
	
	public ZhuSrcService(Properties varp,List<TableColumns> list,VelocityUtil agt){
		this.varp = varp;
		this.list = list;
		this.agt = agt;
	}
	
	public boolean genJavaBean(){
		
		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/Model.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			agt.put("list", list);
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\model\\";
	        String fileName = varp.getProperty("object_name")+"Model.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genIDAO() {
		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/IDAO.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\ibatis\\";
	        String fileName = "I"+varp.getProperty("object_name")+"DAO.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genDAOImpl() {
		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/DAOImpl.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\ibatis\\";
	        String fileName = varp.getProperty("object_name")+"DAOImpl.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genIbatisXml() {
		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/ibatis_xml.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			agt.put("table_name", varp.getProperty("table_name"));
			agt.put("list", list);
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\ibatis\\";
	        String fileName = varp.getProperty("object_name").toLowerCase()+".xml";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genIService() {

		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/IService.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\service\\";
	        String fileName = "I"+varp.getProperty("object_name")+"Service.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genServiceImpl() {

		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/ServiceImpl.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\service\\";
	        String fileName = varp.getProperty("object_name")+"ServiceImpl.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean genAction() {
		boolean result = false;
		try {
			agt.setTemplate(varp.getProperty("templetpath")+"/Action.vm");
			agt.put("package", varp.getProperty("package"));
			agt.put("object_name", varp.getProperty("object_name"));
			agt.put("author", PropertiesUtil.getProperty(varp,"author","UTF-8"));
			agt.put("company",PropertiesUtil.getProperty(varp,"company","UTF-8"));
			
			String path = System.getProperty("user.dir")+"\\"+varp.getProperty("srcfilepath")+"\\struts\\";
	        String fileName = varp.getProperty("object_name")+"Action.java";
	        agt.toFile(path,fileName);
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean genConfig() {
		
		boolean result = false;
		try {
			agt.setTemplate("applicationContext.xml");
			String pk = varp.getProperty("package");
			String object_name = varp.getProperty("object_name");
			String object_desc = PropertiesUtil.getProperty(varp,"object_desc","GBK");
			
			StringBuffer sb = new StringBuffer("<bean id=\""+object_name.toLowerCase()+"DAO\" class=\""+pk+".ibatis."+object_name+"DAOImpl\">\n");
			sb.append("\t\t<property name=\"dataSource\">\n");
			sb.append("\t\t\t<ref local=\"dataSource\" />\n");
			sb.append("\t\t</property>\n");
			sb.append("\t\t<property name=\"sqlMapClient\">\n");
			sb.append("\t\t\t<ref local=\"sqlMapClient\" />\n");
			sb.append("\t\t</property>\n");
			sb.append("\t</bean>\n");
			sb.append("\t<bean id=\""+object_name.toLowerCase()+"Service\" class=\""+pk+".service."+object_name+"ServiceImpl\">\n");
			sb.append("\t\t<property name=\""+object_name.toLowerCase()+"DAO\" ref=\""+object_name.toLowerCase()+"DAO\"/>\n");
			sb.append("\t</bean>\n");
			sb.append("\t<bean id=\""+object_name.toLowerCase()+"Action\" class=\""+pk+".struts."+object_name+"Action\" scope=\"prototype\">\n");
			sb.append("\t\t<property name=\""+object_name.toLowerCase()+"Service\" ref=\""+object_name.toLowerCase()+"Service\"/>\n");
			sb.append("\t</bean>\n");
			sb.append("\t-->\n");
			sb.append("\n");
			sb.append("\t<!-- ${object_desc} manage -->\n");
			sb.append("\t<!--\n");
			sb.append("\t${object_conf}");
			
			agt.put("object_desc", object_desc);
			agt.put("object_conf", sb.toString());

			String path = System.getProperty("user.dir")+"\\src\\";
	        String fileName = "applicationContext.xml";
	        agt.toFile(path,fileName);
	        //---------------------------------------------------------------------------------------
	        agt.setTemplate("sql-map-config.xml");				
			sb = new StringBuffer("<sqlMap resource=\""+pk.replaceAll("\\.", "/")+"/ibatis/"+object_name.toLowerCase()+".xml\" />\n");
			sb.append("\t--> \n");
			sb.append("\t<!-- \n");
			sb.append("\t${object_conf}");
			
			agt.put("object_conf", sb.toString());

			path = System.getProperty("user.dir")+"\\src\\";
	        fileName = "sql-map-config.xml";
	        agt.toFile(path,fileName);
	        
	        result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}

package com.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public class VelocityUtil {
   
	    private VelocityContext context = null;   
	  
	    private Template template = null;   
	  
	    private String templateString = null ;   
	  
	     
	    public void init(String propertiesPath) throws Exception {   
	        if (propertiesPath != null && propertiesPath.trim().length() > 0) {   
	            Velocity.init(propertiesPath);   
	        } else {   
	            Velocity.init();   
	        }   
	        context = new VelocityContext();   
	    }   
	  
	    public void init(Properties properties) throws Exception {   	  
	        Velocity.init(properties);   
	        context = new VelocityContext();   
	    }
	    
	    public void init() throws Exception {
	    	Properties p = new Properties();   
			p.setProperty("resource.loader", "class");   
			p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			//����velocity�ı���
	        p.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
	        p.setProperty(Velocity.INPUT_ENCODING, "GBK");
	        p.setProperty(Velocity.OUTPUT_ENCODING, "GBK"); 
	        
			Velocity.init(p);
			context = new VelocityContext();   
	    }
	  
	    /**    
	     * @param key  
	     * @param value  
	     */  
	    public void put(String key, Object value) {   
	        context.put(key, value);   
	    }   
	  
	    /**  
	     * ����ģ��    
	     * @param templateFile  ģ���ļ�            
	     * @throws Exception  
	     */  
	    public void setTemplate(String templateFile) throws Exception {   
	        try {   
	            template = Velocity.getTemplate(templateFile);   
	        } catch (ResourceNotFoundException rnfe) {   
	            rnfe.printStackTrace();   
	            throw new Exception(" ���� �Ҳ���ģ�� " + templateFile);   
	        } catch (ParseErrorException pee) {   
	            throw new Exception(" ��Ϥģ����� " + templateFile + ":" + pee);   
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	  
	    }   
	  
	    /**  
	     * ����ģ��    
	     * @param templateFile ģ���ļ�  
	     * @throws Exception  
	     */  
	    public void setTemplate(String templateFile, String characterSet)   
	            throws Exception {   
	        try {   
	            template = Velocity.getTemplate(templateFile, characterSet);   
	        } catch (ResourceNotFoundException rnfe) {   
	            rnfe.printStackTrace();   
	            throw new Exception(" ���� �Ҳ���ģ�� " + templateFile);   
	        } catch (ParseErrorException pee) {   
	            throw new Exception(" ��Ϥģ����� " + templateFile + ":" + pee);   
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	  
	    }
	    /**
	     * ��������ת�����ַ���
	     * @param is
	     * @return
	     * @throws IOException
	     */
	    public static String getStringFromStream(InputStream is) throws IOException {
	        if (null == is)
	            return null;
	        try {
	            InputStreamReader reader = new InputStreamReader(is);
	            char[] buffer = new char[1024];
	            StringWriter writer = new StringWriter();
	            int bytes_read;
	            while ((bytes_read = reader.read(buffer)) != -1) {
	                writer.write(buffer, 0, bytes_read);
	            }
	            return (writer.toString());
	        } finally {
	            if (null != is)
	                is.close();
	        }
	    }
	    
	    public void setTemplateString(String templateFile, String characterSet){     
	    	   
	    	try {
	    		InputStream is = this.getClass().getClassLoader().getResourceAsStream(templateFile);
				templateString = getStringFromStream(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	  
	    /**  
	     * ת��Ϊ�ı�  
	     */  
	    public String toText() throws Exception {   
	        StringWriter sw = new StringWriter();   
	        try {   
	            template.merge(context, sw);   
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	        return sw.toString();   
	    }
	    /**  
	     * ת��Ϊ�ı�  
	     */  
	    public String toTextWithTemplateString() throws Exception {   
	        StringWriter sw = new StringWriter();   
	        try {   
	        	Velocity.evaluate(context,sw,"mystring",templateString);  
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	        return sw.toString();   
	    }
	  
	    /**  
	     *   
	     * @param fileName  
	     */  
	    public void toFile(String path,String fileName) throws Exception {   
	        try {
	        	//�滻ģ������
	        	StringWriter sw = new StringWriter();   
	            template.merge(context, sw);
	        	
	        	//����Ŀ¼·��
	        	File dir=new File(path);
	        	if(!dir.exists()){
	        		dir.mkdirs();
	        	}
				//�����ļ�
				File file = new File(dir.getPath(),fileName);
				if(!file.exists()){
					file.createNewFile();
				}
				
		        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		        out.println(sw.toString());   
		        out.close(); 
	            /*
	            PrintWriter filewriter = new PrintWriter(new FileOutputStream(fileName), true);   
	            filewriter.println(sw.toString());   
	            filewriter.close();
	            */
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	  
	    }
	    
	    /**  
	     *   
	     * @param fileName  
	     */  
	    public void toFileWithTemplateString(String path,String fileName) throws Exception {   
	        try {
	        	//�滻ģ������
	        	StringWriter sw = new StringWriter();   
	        	Velocity.evaluate(context,sw,"mystring",templateString);
	        	
	        	//����Ŀ¼·��
	        	File dir=new File(path);
	        	if(!dir.exists()){
	        		dir.mkdirs();
	        	}
				//�����ļ�
				File file = new File(dir.getPath(),fileName+".java");
				if(!file.exists()){
					file.createNewFile();
				}
				
		        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		        out.println(sw.toString());   
		        out.close(); 
	            /*
	            PrintWriter filewriter = new PrintWriter(new FileOutputStream(fileName), true);   
	            filewriter.println(sw.toString());   
	            filewriter.close();
	            */
	        } catch (Exception e) {   
	            throw new Exception(e.toString());   
	        }   
	  
	    }
	  
	    public static void main(String[] args) {   
	    		  
	    }   

}

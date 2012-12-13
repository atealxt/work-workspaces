package com.soa.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.soa.base.Sequences;
import com.soa.userbean.InitDB;

public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String uploadPath = "fileUpload/";
	String tempPath = "fileUpload/temp/";
	String jndiName="jdbc/Collaboration";

	public FileUpload() {
		super();
		System.out.println("文件上传启动");
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		System.out.println("文件上传初始化");
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Logger logger=Logger.getLogger(FileUpload.class);
		String cmd=request.getParameter("cmd");
		String strCode=request.getParameter("strCode");
		String strNo=request.getParameter("strNo");
		if(strNo.equals("undefined")){
			strNo="0";
		}
		String sql="";
		String systemPath=request.getSession().getServletContext().getRealPath("/");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();		
		
		
		//获取JSON
		String cons=" instructions_no='"+strCode+"' and implementation_no='"+strNo+"'";
		String table="annex";
		String sqlContent="*";
		if(cmd.equals("View")){
			out.println(new InitDB().toExtJson(table, sqlContent, Integer.parseInt("0"), Integer.parseInt("20"), cons, jndiName));
		}
		
		//上传文件
		if(cmd.equals("Upload")){
		try {
			logger.info("文件开始上传");
			DiskFileItemFactory factory=new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			factory.setRepository(new File(systemPath+tempPath));
			
			ServletFileUpload upload=new ServletFileUpload(factory);
			upload.setSizeMax(104857600);
			
			List items=upload.parseRequest(request);
			Iterator iter=items.iterator();
			while(iter.hasNext()){
				FileItem item=(FileItem)iter.next();
				String fileName=item.getName();
				String fileType="";
				String newFileName="";
				if(fileName!=null){
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());
					new Sequences(); 
					newFileName=strCode+"_"+strNo+"_"+System.currentTimeMillis();
					fileType=fileName.substring(fileName.lastIndexOf("."),fileName.length());
					item.write(new File(systemPath+uploadPath+newFileName+fileType));
					sql="INSERT INTO annex(instructions_no, implementation_no, annex_name, annex_address)" +
							" VALUES ('"+strCode+"', "+strNo+", '"+fileName+"', '"+uploadPath+newFileName+fileType+"')";

					boolean bolup=new InitDB().execute(sql, jndiName);
					if(bolup){
						out.print("{success:true,msg:'成功'}");
						logger.info("文件上传成功！");
					}else{
						out.print("{success:flase,msg:'失败'}");
						logger.error("文件上传失败！");
					}					
				}
			}
		} catch (Exception e) {
			logger.error("文件上传失败："+e);
			out.print("{success:flase,msg:'失败'}");
		}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

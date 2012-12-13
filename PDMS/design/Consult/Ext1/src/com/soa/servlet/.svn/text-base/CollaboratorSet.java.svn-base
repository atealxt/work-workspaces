package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.soa.userbean.InitDB;


public class CollaboratorSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cmd="";
	private String id="";
	private String unitCode="";
	private String personCode="";
	private String sql="";
	private String jndiName="jdbc/Collaboration";
	private String ids="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//获取前面提交的参数
		cmd=request.getParameter("cmd");
		id=request.getParameter("id");
		unitCode=request.getParameter("unit_code");
		personCode=request.getParameter("personnel_code");
		ids=request.getParameter("json");
		String start=request.getParameter("start");
		if(start==null){
			start="0";
		}
		String limit=request.getParameter("limit");
		if(limit==null){
			limit="10";
		}
		String cons=request.getParameter("name");
		if(cons==null){
			cons="";
		}
		cons=" unit_name like '%"+cons+"%'";
		
		//获取JSON
		String sqlContent=" id,unit_name, personnel_name ";
		String table="project_collaboration a "+
		"left join unit_info b on a.unit_code=b.unit_code "+ 
		"left join personnel_info c on a.personnel_code=c.personnel_code ";		
		if(cmd.equals("List")){
			out.println(new InitDB().toExtJson(table, sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		}
		
		//返回更新结果
		if(cmd.equals("Update")){
			out.print(doUpdate(unitCode, personCode));
		}
		
		//返回新增结果
		if(cmd.equals("Save")){
			out.print(doSave(unitCode, personCode));
		}
		
		//返回删除结果
		if(cmd.equals("Remove")){
			out.print(doRemove(id));
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//更新数据
	public String doUpdate(String unitCode,String personCode){
		sql="UPDATE project_collaboration SET unit_code='"+unitCode+"', personnel_code='"+personCode+"' WHERE unit_code='"+unitCode+"', personnel_code='"+personCode+"'";
		boolean update=new InitDB().execute(sql, jndiName);
		if(update){
			return "{success:true,info:\'更新成功\'}";
		}
		
		return "{failure:true,info:\'更新失败,请与管理员联系\'}";	
	}

	//新增数据
	public String doSave(String unitCode,String personCode){
		sql="INSERT INTO project_collaboration( unit_code, personnel_code) VALUES ('"+unitCode+"', '"+personCode+"')";
		boolean save=new InitDB().execute(sql, jndiName);
		if(save){
			return "{success:true,info:\'添加成功\'}";
		}
		return "{failure:true,info:\'添加失败,请与管理员联系\'}";	
	}
	
	//删除数据
	public String doRemove(String dis){
		StringTokenizer st=new StringTokenizer(ids,",");
		while(st.hasMoreElements()){
			String id=(String) st.nextElement();
			sql="DELETE FROM project_collaboration WHERE id="+id+"";
			boolean re=new InitDB().execute(sql, jndiName);
			if(!re){
				return "删除编号为："+id+"的记录失败";
			}
		}
		return "删除成功";	
	}
}

package com.soa.report;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.soa.userbean.InitDB;


public class ProjectInfoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jndiName="jdbc/Collaboration";
	private String cmd="";
	private String cons="";
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//获得前面提交的数据
		String unit_code=request.getParameter("unit_code");
		String category_code=request.getParameter("category_code");

		cmd=request.getParameter("cmd");
		
		 String start=request.getParameter("start");
		 if(start==null){
			 start="0";
		 }
		 String limit=request.getParameter("limit");
		 if(limit==null){
			 limit="10";
		 }		
		 
		 if(unit_code==null){
			 unit_code="";
		 }
		 if(category_code==null){
			 category_code="";
		 }
		 
		 if((!unit_code.equals(""))&&(!category_code.equals(""))){
			 cons="a.unit_code='"+unit_code+"' and a.category_code="+category_code+"";
		 }else{
			 cons="a.unit_code like '%"+unit_code+"%' and a.category_code like '%"+category_code+"%'";
		 }
		 
		 //表名和所要关连的表
		 String table="collaboration_instructions a "+
						"left join unit_info b  on a.unit_code=b.unit_code "+ 
						"left join  ( " +
							"SELECT a.category_code,b.category_name||'||'||a.category_name as category_name " +
							"FROM collaboration_category a " +
							"left join category_sort b on a.superiors_code=b.category_code " +
							")  c on a.category_code=c.category_code "+
						"left join action d on a.state=d.action_id  "+
						"left join ( "+
						"select instructions_no,count(*)::int4 as count "+
						"from annex "+
						"where implementation_no='0' group by instructions_no "+
						") e on a.instructions_no=e.instructions_no ";
		 //所要查询的字段
		 String sqlContent="a.id, a.instructions_no, unit_name,category_name, request_title,request_context, contract_time,developer_confirm_time,issued_date,require_completion_time, require_personnel,action_name,a.state, remark, isnull(count,0) as annex ";
		 if(cmd.equals("List")){
			 out.println(new InitDB().toExtJson(table,sqlContent,Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		 }	
		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

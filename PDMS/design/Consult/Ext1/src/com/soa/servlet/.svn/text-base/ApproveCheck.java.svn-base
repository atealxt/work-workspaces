package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soa.userbean.InitDB;
import com.soa.userbean.UserInfo;


public class ApproveCheck extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String jndiName="jdbc/Collaboration";
	private String id="";
	private String cmd="";
	private String reason="";
	private String state="";
	private String ids="";
	private String sql="";
	private String checkPerson="";	
	private String unitCode="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();

		//==========获得用户信息==================
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		checkPerson=info.getPersonnelCode();
		unitCode=info.getUnit_code();
		
		cmd=request.getParameter("cmd");
		id=request.getParameter("id");
		reason=request.getParameter("confirmation_reason");
		state=request.getParameter("action_id");
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
		cons=" a.unit_code in (" +
				"select unit_code from project_collaboration where personnel_code='"+checkPerson+"' " +
				") and a.instructions_no like '%"+cons+"%'";
		
		//================================获取JSON==============================
		
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
		 String sqlContent="a.id, a.instructions_no, unit_name,category_name, request_title,request_context, require_completion_time, require_personnel,action_name,a.state, remark,confirmation_reason,confirmation_time, isnull(count,0) as annex ";		

		 if(cmd.equals("List")){
			 out.println(new InitDB().toExtJson(table,sqlContent,Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		 }	
		 
		 
		 //=============================返回更新结果====================================
		 if(cmd.equals("Update")){
			 out.print(doUpdate(id, reason, state));
		 }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//更新数据
	 public String doUpdate(String id,String reason,String state){
		 sql="UPDATE collaboration_instructions "+
			   "SET confirmation_reason='"+reason+"', confirmation_time=now(), confirmation_personnel='"+checkPerson+"', state="+state+" "+
			 "WHERE id="+id+"";
		 boolean update=new InitDB().execute(sql, jndiName);
		 if(update){
				return "{success:true,info:\'更新成功\'}";
			}
			
			return "{failure:true,info:\'更新失败,请与管理员联系\'}";
	 }	
	
	
}
